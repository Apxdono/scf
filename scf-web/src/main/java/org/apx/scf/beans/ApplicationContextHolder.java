package org.apx.scf.beans;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by oleg on 02.04.2015.
 */
@Component(value = "appContextHolder")
@Scope
public class ApplicationContextHolder implements Serializable {

    static SimpleGrantedAuthority ANONYMOUS_AUTHORITY = new SimpleGrantedAuthority("ROLE_ANONYMOUS");

    @Autowired
    ApplicationContext ctx;

    public static ApplicationContext context(){
        return instance().ctx;
    }

    public SecurityContext getSecurityContextHolder() {
        return SecurityContextHolder.getContext();
    }

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isFullyAuthenticated(){
        return !SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(ANONYMOUS_AUTHORITY);
    }

    public static ApplicationContextHolder instance(){
        return SingletonHolder.instance;
    }

    protected ApplicationContextHolder(){
        SingletonHolder.instance = this;
    }

    protected static class SingletonHolder{
        static ApplicationContextHolder instance;
    }

    public static boolean isProfile(String profileName){
        return ArrayUtils.contains(instance().ctx.getEnvironment().getActiveProfiles(),profileName+"");
    }



}
