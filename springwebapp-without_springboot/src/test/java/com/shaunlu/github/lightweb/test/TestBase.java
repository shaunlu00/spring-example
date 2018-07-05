package com.shaunlu.github.lightweb.test;

import com.shaunlu.github.lightweb.config.APPConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
// As APPConfiguration includes web and security configurations, @WebAppConfiguration must be added even you only need to test DAL or service bean
@WebAppConfiguration
@ContextConfiguration(classes = {APPConfiguration.class})
public class TestBase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
