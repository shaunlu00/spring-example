package com.shaunlu.github.lightweb.config;

import com.shaunlu.github.lightweb.config.database.DatabaseConfiguration;
import com.shaunlu.github.lightweb.config.logging.LoggingConfig;
import com.shaunlu.github.lightweb.config.property.ApplicationProperty;
import com.shaunlu.github.lightweb.config.security.SecurityConfiguration;
import com.shaunlu.github.lightweb.config.web.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Annotation Config
@Configuration
@Import({ApplicationProperty.class, LoggingConfig.class, DatabaseConfiguration.class, WebConfig.class, SecurityConfiguration.class})
// Spring AOP
@EnableAspectJAutoProxy
// Define bean scan path
@ComponentScan(basePackages = {"com.shaunlu.github.lightweb"})
// Enable Spring Data JPA
@EnableJpaRepositories(basePackages = {"com.shaunlu.github.lightweb.repository"})
// Enable JPA Auditing
@EnableJpaAuditing
// Enable Spring Transaction
@EnableTransactionManagement
public class APPConfiguration {

    Logger logger = LoggerFactory.getLogger(this.getClass());

}
