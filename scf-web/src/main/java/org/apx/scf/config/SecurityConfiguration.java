package org.apx.scf.config;

import org.apx.scf.config.extra.DevLFEntryPoint;
import org.apx.scf.config.extra.LFEntryPoint;
import org.apx.scf.config.extra.LoginErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * Created by Oleg on 02.11.2015.
 */
@Configuration
@ComponentScan("org.apx.scf.security")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    static final String USER_QUERY = "SELECT usr_login as principal, usr_password as credentials, enabled from users WHERE usr_login = ?";
    static final String AUTHORITIES_QUERY = "SELECT user_id as principal, role_name as authority FROM user_roles WHERE user_id = ?";

    @Autowired
    ApplicationContext context;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint aep = (AuthenticationEntryPoint) context.getBean("entryPoint");
        http.csrf().disable()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(aep, new AntPathRequestMatcher("/rest/api/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(aep, new AntPathRequestMatcher("/user/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(aep, new AntPathRequestMatcher("/test"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(aep, new AntPathRequestMatcher("/websocket/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(aep, new AntPathRequestMatcher("/secure/**"))
                .and().authorizeRequests().antMatchers("/rest/api/**").authenticated()
                .and().authorizeRequests().antMatchers("/test").authenticated()
                .and().authorizeRequests().antMatchers("/secure/**").authenticated()
                .and().authorizeRequests().antMatchers("/user/**").authenticated()
                .and().authorizeRequests().antMatchers("/websocket/**").authenticated()
                .and().authorizeRequests().anyRequest().permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).permitAll()
                .and().formLogin().failureHandler(loginErrorHandler()).
                passwordParameter("password").usernameParameter("username").loginProcessingUrl("/login").defaultSuccessUrl("/");

        http.addFilterBefore(characterEncodingFilter(), AnonymousAuthenticationFilter.class);
//        http.addFilterBefore(characterEncodingFilter(),AnonymousAuthenticationFilter.class);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.userDetailsService(authDetails).
//                passwordEncoder(new Md5PasswordEncoder());
        auth.inMemoryAuthentication()
                .withUser("Oleg")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .password("oleg")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .roles("USER","ADMIN");

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Bean(name = "springSecurityFilterChain")
//    public DelegatingFilterProxy springSecurityFilterChain(){
//        return new DelegatingFilterProxy("springSecurityFilterChain");
//    }

    @Bean
    public LoginErrorHandler loginErrorHandler() {
        return new LoginErrorHandler();
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

//    @Profile("dev")
//    @Bean(name = "entryPoint")
//    public AuthenticationEntryPoint entryPointDev() {
//        return new DevLFEntryPoint("/login");
//    }

//    @Profile("prod")
    @Bean(name = "entryPoint")
    public AuthenticationEntryPoint entryPointProd() {
        return new LFEntryPoint("/login");
    }

//    @Bean
//    public SecurityEvaluationContextExtension2 securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension2();
//    }
}
