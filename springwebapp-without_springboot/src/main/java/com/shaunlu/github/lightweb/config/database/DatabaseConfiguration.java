package com.shaunlu.github.lightweb.config.database;

import com.shaunlu.github.lightweb.config.property.ApplicationProperty;
import com.shaunlu.github.lightweb.util.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


@Configuration
public class DatabaseConfiguration {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApplicationProperty applicationProperty;

    @Bean
    DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(20);
        config.setDataSourceClassName(applicationProperty.getDataAccess().getDriverClassName());
        config.addDataSourceProperty("url", applicationProperty.getDataAccess().getUrl());
//        config.addDataSourceProperty("databaseName", applicationProperty.getDataSource().getDbName());
        config.addDataSourceProperty("user", applicationProperty.getDataAccess().getUserName());
        config.addDataSourceProperty("password", applicationProperty.getDataAccess().getPassword());
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        // configure hibernate as jpa provider
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPackagesToScan("com.shaunlu.github.lightweb.domain");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", applicationProperty.getDataAccess().getHibernateDialect());
        properties.put("hibernate.show_sql", applicationProperty.getDataAccess().getHibernateShowSQL());
//        properties.put("hibernate.hbm2ddl.auto", applicationProperty.getDataSource().getHibernateHBM2DDL());
        return properties;
    }



    // start H2 server at development environment
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
    public Server h2TCPServer() throws SQLException {
        return Server.createTcpServer("-tcp","-tcpAllowOthers");
    }

    // init DB
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:init/db_schema.xml");
        liquibase.setDropFirst(true);
        if (applicationProperty.getDataAccess().getLiquibaseEnabled()){
            liquibase.setShouldRun(true);
        } else {
            liquibase.setShouldRun(false);
        }
        return liquibase;
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditorAware();
    }

}
