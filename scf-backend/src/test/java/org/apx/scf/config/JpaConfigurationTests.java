package org.apx.scf.config;

import org.apx.scf.domain.DummyObject;
import org.apx.scf.repository.DummyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by oleg on 19.06.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class},loader = AnnotationConfigContextLoader.class)
@ActiveProfiles({"dev"})
@TransactionConfiguration(defaultRollback=true)
public class JpaConfigurationTests {

    static final Logger LOG = LoggerFactory.getLogger(JpaConfigurationTests.class);

    @Autowired
    @Qualifier("jpaProperties")
    Properties jpaProperties;

    @Autowired
    DataSource ds;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    DummyRepository repo;

    static String profileName = "dev";

    @Test
    public void testJpaProperties(){
        assertThat("Properties were not loaded",jpaProperties,notNullValue());
        assertThat("Weaving should be disabled",jpaProperties.getProperty("eclipselink.weaving"),is("false"));
    }

    @Test
    public void testDatasource() throws SQLException {
        assertThat("Datasource must not be null",ds,notNullValue());
        assertThat("Connection must not be null",ds.getConnection(),notNullValue());
        assertThat("Entity manager must not be null",entityManager,notNullValue());
        assertThat("Failed to query database", (Integer)entityManager.createNativeQuery("SELECT 1").getSingleResult(),equalTo(Integer.valueOf(1)));
    }

    @Test
    @Transactional
    public void testEntityManager() throws SQLException {
        DummyObject dummy = getDummy(null,"Test dummy object using entity manager");
        entityManager.persist(dummy);
        assertThat("No id was assigned for object during persist", dummy.getId(), notNullValue());
        assertThat("Object was not persisted",entityManager.find(DummyObject.class,dummy.getId()),notNullValue());
    }

    @Test
    @Transactional
    public void testRepository() throws SQLException {
        DummyObject dummy = getDummy(null,"Test dummy object using repository");
        DummyObject d2 = repo.save(dummy);
        assertThat("No id was assigned for object during persist", d2.getId(), notNullValue());
        assertThat("Object was not persisted", repo.findOne(d2.getId()), notNullValue());
    }


    public static DummyObject getDummy(String name, String systemName){
        DummyObject dummy = new DummyObject();
        dummy.setDisplayName(name != null ? name : "Test dummy object");
        dummy.setSystemName(systemName != null ? systemName : "Test dummy object");
        return dummy;
    }

}
