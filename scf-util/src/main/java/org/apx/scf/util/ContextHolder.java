package org.apx.scf.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by oleg on 19.06.2015.
 */
@Component
@Scope(value = "singleton")
public class ContextHolder {
    @Autowired
    ApplicationContext ctx;

    public static ApplicationContext context(){
        return instance().ctx;
    }

    public static ContextHolder instance(){
        return SingletonHolder.instance;
    }

    protected ContextHolder(){
        SingletonHolder.instance = this;
    }

    protected static class SingletonHolder{
        static ContextHolder instance;
    }
}
