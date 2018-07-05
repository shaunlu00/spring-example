package com.shaunlu.github.lightweb.test.configuration;

import com.shaunlu.github.lightweb.test.TestBase;
import com.shaunlu.github.lightweb.web.rest.ShoppingController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class LoggingTest extends TestBase {

    @Autowired
    ShoppingController shoppingController;

    @Test
    public void testLoggingAspect(){
        shoppingController.getComputer();
    }

    @Test
    public void testLogStash(){

    }
}
