spring:
    application:
        name: ${application_name}
    profiles:
        active: dev
        include: swagger
    liquibase:
        changeLog: "classpath:/config/liquibase/master.xml"
        contexts: dev
    jackson:
        serialization.indent_output: true
        time-zone: "GMT+08:00"
    datasource:
        type: com.zaxxer.hikari.HikariDataSource
        url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=- 1;DB_CLOSE_ON_EXIT=FALSE
        hikari:
            data-source-properties:
                cachePrepStmts: true
                prepStmtCacheSize: 250
                prepStmtCacheSqlLimit: 2048
                useServerPrepStmts: true
    jpa:
        database-platform: org.hibernate.dialect.H2Dialect
        database: H2
        show-sql: true
        properties:
            hibernate.id.new_generator_mappings: true
            hibernate.cache.use_second_level_cache: false
            hibernate.cache.use_query_cache: false
            hibernate.generate_statistics: true
    h2:
      console:
        enabled: true
        path: /h2-console
        settings:
          web-allow-others: true


logging:
    level:
        ROOT: DEBUG
        ${package}: DEBUG

server:
    port: ${application_port}


application:
    mail:
        host: 127.0.0.1
        port: 25
        auth_user_name: admin
        auth_password: admin
        sender_address: test@example.com
        encode: UTF-8

