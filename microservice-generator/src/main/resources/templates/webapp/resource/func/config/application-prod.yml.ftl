spring:
    application:
        name: ${application_name}
    profiles:
        active: prod
        include: swagger
    liquibase:
        changeLog: "classpath:/config/liquibase/master.xml"
        contexts: dev
    jackson:
        serialization.indent_output: true
        time-zone: "GMT+08:00"
    datasource:
        type: com.zaxxer.hikari.HikariDataSource
        url: jdbc:mysql://localhost:3309/example?useUnicode=true&characterEncoding=utf8&useSSL=false
        username: example
        password: 'example'
        hikari:
            data-source-properties:
                cachePrepStmts: true
                prepStmtCacheSize: 250
                prepStmtCacheSqlLimit: 2048
                useServerPrepStmts: true
    jpa:
        database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
        database: MYSQL
        show-sql: true
        properties:
            hibernate.id.new_generator_mappings: true
            hibernate.cache.use_second_level_cache: false
            hibernate.cache.use_query_cache: false
            hibernate.generate_statistics: true

logging:
    level:
        ROOT: INFO
        ${package}: INFO

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
    jwt:
        secret_key: a9jgu#;asu!iasdl1js
        token_validity_in_seconds: 3600

