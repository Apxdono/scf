package org.apx.scf.domain.test;

import org.apx.scf.domain.DummyObject;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by oleg on 10/18/15.
 */
public class DummyObjectTests {

    @Test
    public void equalsBuilderTest(){
        DummyObject a = new DummyObject();
        DummyObject b = new DummyObject();
        String ida = UUID.randomUUID().toString().toUpperCase();
        String idb = UUID.randomUUID().toString().toUpperCase();
        a.setId(ida);
        b.setId(idb);
        a.setDisplayName("Test object");
        a.setSystemName("test object {" + ida + "}");
        b.setDisplayName("Test object");
        b.setSystemName("test object {" + idb + "}");
        Assert.assertThat("Objects must not match by id",a, CoreMatchers.not(CoreMatchers.equalTo(b)));
        b.setId(ida);
        Assert.assertThat("Objects must match by id",a, CoreMatchers.equalTo(b));
    }
}
