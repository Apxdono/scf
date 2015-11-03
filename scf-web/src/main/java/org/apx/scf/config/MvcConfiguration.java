package org.apx.scf.config;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by oleg on 19.06.2015.
 */
@Configuration
@EnableWebMvc
@EnableAsync
@ComponentScan("org.apx.scf.web")
@Import({ThymeleafConfiguration.class})
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    static final int CACHE_PERIOD = 3600;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("js/**").addResourceLocations("/WEB-INF/static/js/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("css/**").addResourceLocations("/WEB-INF/static/css/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("images/**").addResourceLocations("/WEB-INF/static/images/").setCachePeriod(CACHE_PERIOD);
    }


    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/static/html/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
