package org.apx.scf.config;

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
public class JpaConfigurationTests {

    static final Logger LOG = LoggerFactory.getLogger(JpaConfigurationTests.class);

    @Autowired
    @Qualifier("jpaProperties")
    Properties jpaProperties;

    @Autowired
    DataSource ds;

    @Test
    public void testJpaProperties(){
        assertThat("Properties were not loaded",jpaProperties,notNullValue());
        assertThat("Weaving should be disabled",jpaProperties.getProperty("eclipselink.weaving"),is("false"));
    }

    @Test
    public void testDatasource() throws SQLException {
        assertThat("Datasource must not be null",ds,notNullValue());
        Connection connection = ds.getConnection();
        assertThat("Connection must not be null",connection,notNullValue());
        assertTrue("SELECT 1 failed", connection.createStatement().execute("SELECT 1"));
    }

}
