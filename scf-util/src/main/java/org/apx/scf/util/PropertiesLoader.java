package org.apx.scf.util;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPathFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by oleg on 19.06.2015.
 */
public abstract class PropertiesLoader {


    /**
     * Loads properties from classpath
     * @param location in classpath
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String location) throws IOException {
        PropertiesFactoryBean bean  = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource(location));
        bean.afterPropertiesSet();
        return bean.getObject();
    }

}
