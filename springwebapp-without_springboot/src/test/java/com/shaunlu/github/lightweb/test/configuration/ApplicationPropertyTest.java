package com.shaunlu.github.lightweb.test.configuration;

import com.shaunlu.github.lightweb.config.property.ApplicationProperty;
import com.shaunlu.github.lightweb.test.TestBase;
import org.junit.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationPropertyTest extends TestBase {

    @Autowired
    ApplicationProperty applicationProperty;

    @Test
    public void testApplicationProperty(){
        Assert.assertEquals("lightweb", applicationProperty.getName());
        Assert.assertEquals("org.h2.jdbcx.JdbcDataSource", applicationProperty.getDataAccess().getDriverClassName());
    }
}
