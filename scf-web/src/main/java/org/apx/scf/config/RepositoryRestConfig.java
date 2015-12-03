package org.apx.scf.config;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ManagedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Oleg on 03.12.2015.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {


    @PersistenceContext
    EntityManager em;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.setBasePath("/rest/api");
        config.setReturnBodyOnCreate(true).setReturnBodyOnUpdate(true);
        Set<ManagedType<?>> l  = em.getMetamodel().getManagedTypes();
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (ManagedType<?> type : l) {
            classes.add(type.getJavaType());
        }
        config.exposeIdsFor(classes.toArray(new Class<?>[]{}));
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        super.configureJacksonObjectMapper(objectMapper);
        objectMapper.enable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }


    //    @Override
//    public ObjectMapper halObjectMapper() {
//        ObjectMapper om = super.halObjectMapper();
//        om.setFilters(new SimpleFilterProvider().addFilter("excludeBlobs", new BlobPropertyFilter()));
//        return om;
//    }

}

