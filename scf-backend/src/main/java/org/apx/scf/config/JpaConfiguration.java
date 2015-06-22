package org.apx.scf.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apx.scf.util.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by oleg on 19.06.2015.
 */
@Configuration
@ComponentScan("org.apx.scf.repository;org.apx.scf.service")
public class JpaConfiguration {

    @Bean(name="jpaProperties") @Profile("dev")
    public Properties jpaPropertiesDev() throws IOException {
        return PropertiesLoader.loadProperties("/jpa/jpa-dev.properties");
    }

    @Bean(name="jpaProperties") @Profile("prod")
    public Properties jpaPropertiesProd() throws IOException {
        return PropertiesLoader.loadProperties("/jpa/jpa-prod.properties");
    }

    @Bean(name="jdbcProperties") @Profile("dev")
    public Properties jdbcPropertiesDev() throws IOException {
        return PropertiesLoader.loadProperties("/jpa/jdbc-dev.properties");
    }

    @Bean(name="jdbcProperties") @Profile("prod")
    public Properties jdbcPropertiesProd() throws IOException {
        return PropertiesLoader.loadProperties("/jpa/jdbc-prod.properties");
    }


    @Bean(name="dataSource") @Autowired
    public DataSource dataSource(@Qualifier("jdbcProperties") Properties properties){
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDriverClass(properties.getProperty("db.driver"));
        ds.setJdbcUrl(properties.getProperty("db.url"));
        ds.setUsername(properties.getProperty("db.login"));
        ds.setPassword(properties.getProperty("db.password"));
        return ds;
    }




}
