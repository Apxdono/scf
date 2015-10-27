package org.apx.scf.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apx.scf.config.util.jpa.audit.AuditingDateTimeProvider;
import org.apx.scf.config.util.jpa.audit.CurrentTimeDateTimeService;
import org.apx.scf.config.util.jpa.audit.DateTimeService;
import org.apx.scf.util.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by oleg on 19.06.2015.
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
@EnableJpaRepositories(basePackages = "org.apx.scf.repository")
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@ComponentScan("org.apx.scf.service")
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

    @Autowired
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(
            @Qualifier("jdbcProperties") Properties jdbcProperties,
            @Qualifier("jpaProperties") Properties jpaProperties,
            @Qualifier("dataSource") DataSource ds,
            @Qualifier("jpaVendorAdapter") JpaVendorAdapter vendor){
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setJpaVendorAdapter(vendor);
        emf.setJpaDialect(new EclipseLinkJpaDialect());
        emf.setJpaProperties(jpaProperties);
        emf.setPersistenceUnitName("scf_unit");
        emf.setPackagesToScan(jdbcProperties.getProperty("db.packages.to.scan").split(";"));
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter(){
        JpaVendorAdapter vendor = new EclipseLinkJpaVendorAdapter();
        return vendor;
    }

    @Bean(name = "transactionManager")
    @Autowired
    public JpaTransactionManager transactionManager(
            @Qualifier("jpaDialect") JpaDialect dialect,
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("entityManagerFactory") EntityManagerFactory emf){
        JpaTransactionManager txm = new JpaTransactionManager();
        txm.setJpaDialect(dialect);
        txm.setDataSource(dataSource);
        txm.setEntityManagerFactory(emf);
        return txm;
    }


    @Bean(name = "jpaDialect")
    public JpaDialect jpaDialect(){
        return new EclipseLinkJpaDialect();
    }

    @Bean(name = "dateTimeService")
    public DateTimeService dateTimeService(){
        return new CurrentTimeDateTimeService();
    }

    @Bean(name = "dateTimeProvider")
    @Autowired
    public DateTimeProvider dateTimeProvider(DateTimeService dateTimeService){
        return new AuditingDateTimeProvider(dateTimeService);
    }

}
