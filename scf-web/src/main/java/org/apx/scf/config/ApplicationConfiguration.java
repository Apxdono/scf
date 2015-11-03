package org.apx.scf.config;

import org.springframework.context.annotation.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.FormattingConversionService;

/**
 * Created by oleg on 19.06.2015.
 */
@Configuration
@ComponentScan(basePackages = "org.apx.scf.beans")
@Import({JpaConfiguration.class, SecurityConfiguration.class})
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @Bean(name = "mvcConversionService")
    public ConversionService mvcConversionService(){
        return new FormattingConversionService();
    }
}
