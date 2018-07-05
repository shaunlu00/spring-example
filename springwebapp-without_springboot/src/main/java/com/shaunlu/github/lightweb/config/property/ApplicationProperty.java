package com.shaunlu.github.lightweb.config.property;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Application Properties Container
 */

@Configuration
@PropertySource({"classpath:application-${spring.profiles.active}.properties"})
public class ApplicationProperty {

    public ApplicationProperty(Environment env){
        this.dataAccess = new DataAccess();
        dataAccess.setDriverClassName(env.getProperty("database.driver-class-name"));
        dataAccess.setUrl(env.getProperty("database.url"));
        dataAccess.setDbName(env.getProperty("database.dbname"));
        dataAccess.setUserName(env.getProperty("database.username"));
        dataAccess.setPassword(env.getProperty("database.password"));
        dataAccess.setHibernateDialect(env.getProperty("database.hibernate.dialect"));
        dataAccess.setHibernateShowSQL(env.getProperty("database.hibernate.show_sql"));
        dataAccess.setHibernateHBM2DDL(env.getProperty("database.hibernate.hbm2ddl.auto"));
        dataAccess.setLiquibaseEnabled(Boolean.valueOf(env.getProperty("database.liquibase.enable")));

        this.logging = new Logging();
        logging.setLocalLogLevel(env.getProperty("log.loglevel"));
        logging.setLocalLogFilePath(env.getProperty("log.logfilepath"));
        logging.setLogstashEnabled(Boolean.valueOf(env.getProperty("logstash.enabled")));
        logging.setLogstashHost(env.getProperty("logstash.host"));
        logging.setLogstashPort(env.getProperty("logstash.port"));

        this.webSecurity = new WebSecurity();
        webSecurity.setJwtSecretKey(env.getProperty("jwt.secretkey"));
        webSecurity.setTokenValidityInSeconds(Integer.valueOf(env.getProperty("jwt.token-validity-inseconds")));
        webSecurity.setTokenValidityInSecondsForRememberMe(Integer.valueOf(env.getProperty("jwt.token-validity-rememberme-inseconds")));
    }

    @Value("${application.name}")
    private String name;

    @Value("${application.host}")
    private String host;

    @Value("${application.port}")
    private String port;

    private DataAccess dataAccess;

    private Logging logging;

    private WebSecurity webSecurity;

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public DataAccess getDataAccess() {
        return dataAccess;
    }

    public Logging getLogging() {
        return logging;
    }

    public WebSecurity getWebSecurity() {return webSecurity; }


    /**
     * Data Access Configuration
     */
    public class DataAccess {

        private String driverClassName;

        private String url;

        private String dbName;

        private String userName;

        private String password;

        private String hibernateDialect;

        private String hibernateShowSQL;

        private String hibernateHBM2DDL;

        private Boolean isLiquibaseEnabled;

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setHibernateDialect(String hibernateDialect) {
            this.hibernateDialect = hibernateDialect;
        }

        public void setHibernateShowSQL(String hibernateShowSQL) {
            this.hibernateShowSQL = hibernateShowSQL;
        }

        public void setHibernateHBM2DDL(String hibernateHBM2DDL) {
            this.hibernateHBM2DDL = hibernateHBM2DDL;
        }

        public String getHibernateDialect() {
            return hibernateDialect;
        }

        public String getHibernateShowSQL() {
            return hibernateShowSQL;
        }

        public String getHibernateHBM2DDL() {
            return hibernateHBM2DDL;
        }

        public String getDbName() {
            return dbName;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public Boolean getLiquibaseEnabled() {
            return isLiquibaseEnabled;
        }

        public void setLiquibaseEnabled(Boolean liquibaseEnabled) {
            isLiquibaseEnabled = liquibaseEnabled;
        }
    }

    /**
     * Logging Configuration Properties
     */
    public class Logging {

        private String localLogLevel;

        private String localLogFilePath;

        private boolean logstashEnabled;

        private String logstashHost;

        private String logstashPort;

        public String getLocalLogLevel() {
            return localLogLevel;
        }

        public void setLocalLogLevel(String localLogLevel) {
            this.localLogLevel = localLogLevel;
        }

        public String getLocalLogFilePath() {
            return localLogFilePath;
        }

        public void setLocalLogFilePath(String localLogFilePath) {
            this.localLogFilePath = localLogFilePath;
        }

        public boolean isLogstashEnabled() {
            return logstashEnabled;
        }

        public void setLogstashEnabled(boolean logstashEnabled) {
            this.logstashEnabled = logstashEnabled;
        }

        public String getLogstashHost() {
            return logstashHost;
        }

        public void setLogstashHost(String logstashHost) {
            this.logstashHost = logstashHost;
        }

        public String getLogstashPort() {
            return logstashPort;
        }

        public void setLogstashPort(String logstashPort) {
            this.logstashPort = logstashPort;
        }
    }

    public class WebSecurity{
        private String jwtSecretKey;

        private Integer tokenValidityInSeconds;

        private Integer tokenValidityInSecondsForRememberMe;

        public Integer getTokenValidityInSeconds() {
            return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(Integer tokenValidityInSeconds) {
            this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

        public Integer getTokenValidityInSecondsForRememberMe() {
            return tokenValidityInSecondsForRememberMe;
        }

        public void setTokenValidityInSecondsForRememberMe(Integer tokenValidityInSecondsForRememberMe) {
            this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
        }

        public String getJwtSecretKey() {
            return jwtSecretKey;
        }

        public void setJwtSecretKey(String jwtSecretKey) {
            this.jwtSecretKey = jwtSecretKey;
        }
    }
}
