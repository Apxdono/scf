package org.apx.scf.initializer;

import org.apache.commons.lang3.StringUtils;
import org.apx.scf.config.ApplicationConfiguration;
import org.apx.scf.config.MvcConfiguration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

/**
 * Created by oleg on 19.06.2015.
 */
public class WebappInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApplicationConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        applicationContext.refresh();
        servletContext.setInitParameter("spring.profiles.default", StringUtils.
                join(applicationContext.getEnvironment().getActiveProfiles(),","));

        CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding("UTF-8");
        cef.setForceEncoding(true);

        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MvcConfiguration.class);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);

        servletContext.addFilter("characterEncodingFilter", cef).
                addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter(
                AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME,
                DelegatingFilterProxy.class)
                .addMappingForUrlPatterns(null, false, "/*");

    }


}
