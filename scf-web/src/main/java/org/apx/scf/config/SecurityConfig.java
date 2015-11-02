package org.apx.scf.config;

import org.apx.scf.config.extra.LFEntryPoint;
import org.apx.scf.config.extra.LoginErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * Created by Oleg on 02.11.2015.
 */
@Configuration
@Profile({"test", "prod"})
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    static final String USER_QUERY = "SELECT usr_login as principal, usr_password as credentials, enabled from users WHERE usr_login = ?";
    static final String AUTHORITIES_QUERY = "SELECT user_id as principal, role_name as authority FROM user_roles WHERE user_id = ?";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(entryPoint(), new AntPathRequestMatcher("/rest/api/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(entryPoint(), new AntPathRequestMatcher("/user/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(entryPoint(), new AntPathRequestMatcher("/test/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(entryPoint(), new AntPathRequestMatcher("/websocket/**"))
                .and().exceptionHandling().defaultAuthenticationEntryPointFor(entryPoint(), new AntPathRequestMatcher("/secure/**"))
                .and().authorizeRequests().antMatchers("/rest/api/**").authenticated()
                .and().authorizeRequests().antMatchers("/test/**").authenticated()
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
        auth.inMemoryAuthentication().withUser("Oleg").accountExpired(false).accountLocked(false).credentialsExpired(false).password("oleg").authorities("ROLE_USER", "ROLE_ADMIN");

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

    @Bean
    public LFEntryPoint entryPoint() {
        return new LFEntryPoint("/login");
    }

//    @Bean
//    public SecurityEvaluationContextExtension2 securityEvaluationContextExtension() {
//        return new SecurityEvaluationContextExtension2();
//    }
}
