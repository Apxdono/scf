package org.apx.scf.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by oleg on 19.06.2015.
 */
@Configuration
@Import({JpaConfiguration.class,SecurityConfig.class})
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.apx.scf.util")
public class ApplicationConfiguration {

}
