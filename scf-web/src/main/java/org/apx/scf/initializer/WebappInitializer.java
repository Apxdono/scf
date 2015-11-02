package org.apx.scf.initializer;

import org.apx.scf.config.ApplicationConfiguration;
import org.apx.scf.config.JpaConfiguration;
import org.apx.scf.config.MvcConfiguration;
import org.apx.scf.config.SecurityConfig;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by oleg on 19.06.2015.
 */
//@Order(2)
//public class WebappInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[]{ApplicationConfiguration.class,SecurityConfig.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{MvcConfiguration.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//        encodingFilter.setEncoding("UTF-8");
//        encodingFilter.setForceEncoding(true);;
//        return new Filter[]{encodingFilter};
//    }
//
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//
//    }
//
//    //    @Override
////    public void onStartup(ServletContext servletContext) throws ServletException {
////        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
////        applicationContext.register(ApplicationConfiguration.class);
////        applicationContext.refresh();
////        servletContext.setInitParameter("defaultHtmlEscape", "true");
////        servletContext.addListener(new ContextLoaderListener(applicationContext));
////
////        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
////        encodingFilter.setInitParameter("encoding", "UTF-8");
////        encodingFilter.setInitParameter("forceEncoding", "true");
////        encodingFilter.addMappingForUrlPatterns(null, true, "/*");
////
//////        FilterRegistration.Dynamic securityFilter = servletContext.addFilter(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME, DelegatingFilterProxy.class);
//////        securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
////
////        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
////        ctx.register(MvcConfiguration.class);
////
////
////
////        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
////        servlet.addMapping("/");
////        servlet.setLoadOnStartup(1);
////        servlet.setAsyncSupported(true);
////
////
////
////    }
//
//
//
//}
