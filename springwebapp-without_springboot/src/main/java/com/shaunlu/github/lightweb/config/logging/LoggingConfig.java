package com.shaunlu.github.lightweb.config.logging;

import ch.qos.logback.classic.LoggerContext;
import com.shaunlu.github.lightweb.config.property.ApplicationProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by shaun on 17-11-21.
 */
@Configuration
public class LoggingConfig {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationProperty applicationProperty;

    private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    @PostConstruct
    public void init(){
        if(applicationProperty.getLogging().isLogstashEnabled()){
            addLogStashAppender();
        }
    }

    private void addLogStashAppender(){}

}
