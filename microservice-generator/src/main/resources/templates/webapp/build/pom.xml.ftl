<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${package_name}</groupId>
    <artifactId>${application_name}</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>${application_name}</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.1.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <!-- Build properties -->
        <spring-boot.version>2.0.1.RELEASE</spring-boot.version>
        <maven.version>3.0.0</maven.version>
        <maven-war-plugin.version>3.1.0</maven-war-plugin.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
        <jackson.version>2.9.1</jackson.version>
        <dropwizard.metrics.version>4.0.1</dropwizard.metrics.version>
        <!-- These remain empty unless the corresponding profile is active -->
        <profile.no-liquibase/>
        <profile.swagger/>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
        <dependency>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-core</artifactId>
        </dependency>
        <!--
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>
        -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-data</artifactId>
        </dependency>

        <!-- ehcache -->
        <dependency>
            <groupId>org.ehcache</groupId>
            <artifactId>ehcache</artifactId>
            <version>3.5.2</version>
        </dependency>

        <!-- jackson -->
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-hibernate5</artifactId>
            <version>${r'${jackson.version}'}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-hppc</artifactId>
            <version>${r'${jackson.version}'}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
            <version>${r'${jackson.version}'}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-json-org</artifactId>
            <version>${r'${jackson.version}'}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.module</groupId>
            <artifactId>jackson-module-afterburner</artifactId>
            <version>${r'${jackson.version}'}</version>
        </dependency>

        <!-- problem -->
        <dependency>
            <groupId>org.zalando</groupId>
            <artifactId>problem-spring-web</artifactId>
            <version>0.22.5</version>
        </dependency>

        <!-- Hikari -->
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.8.0</version>
            <scope>compile</scope>
        </dependency>

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.8.0</version>
            <scope>compile</scope>
        </dependency>

        <!-- jjwt -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <!-- maven enforcer -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-enforcer-plugin</artifactId>
                <version>3.0.0-M1</version>
                <executions>
                    <execution>
                        <id>enforce-versions</id>
                        <goals>
                            <goal>enforce</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <rules>
                        <requireMavenVersion>
                            <message>You are running an older version of Maven. JHipster requires at least Maven
							${r'${maven.version}'}
                            </message>
                            <version>[${r'${maven.version}'},)</version>
                        </requireMavenVersion>
                        <requireJavaVersion>
                            <message>You are running an incompatible version of Java. This application requires JDK
							${r'${java.version}'}
                            </message>
                            <version>[1.8,1.9)</version>
                        </requireJavaVersion>
                    </rules>
                </configuration>
            </plugin>


            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${r'${spring-boot.version}'}</version>
            </plugin>
        </plugins>
    </build>

    <profiles>
        <profile>
            <id>no-liquibase</id>
            <properties>
                <profile.no-liquibase>,no-liquibase</profile.no-liquibase>
            </properties>
        </profile>
        <profile>
            <id>swagger</id>
            <properties>
                <profile.swagger>,swagger</profile.swagger>
            </properties>
        </profile>
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-undertow</artifactId>
                </dependency>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-devtools</artifactId>
                    <optional>true</optional>
                </dependency>
            </dependencies>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-war-plugin</artifactId>
                        <version>${r'${maven-war-plugin.version}'}</version>
                        <configuration>
                            <failOnMissingWebXml>false</failOnMissingWebXml>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
            <properties>
                <!-- default Spring profiles -->
                <spring.profiles.active>dev${r'${profile.no-liquibase}'}</spring.profiles.active>
            </properties>
        </profile>

        <profile>
            <id>prod</id>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-undertow</artifactId>
                </dependency>
            </dependencies>
            <build>
                <plugins>
                    <plugin>
                        <artifactId>maven-clean-plugin</artifactId>
                        <version>3.0.0</version>
                        <configuration>
                            <filesets>
                                <fileset>
                                    <directory>target/www/</directory>
                                </fileset>
                            </filesets>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-war-plugin</artifactId>
                        <version>${r'${maven-war-plugin.version}'}</version>
                        <configuration>
                            <failOnMissingWebXml>false</failOnMissingWebXml>
                        </configuration>
                    </plugin>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <version>${r'${spring-boot.version}'}</version>
                        <configuration>
                            <mainClass>${r'${start-class}'}</mainClass>
                            <executable>true</executable>
                        </configuration>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>build-info</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                    <plugin>
                        <groupId>pl.project13.maven</groupId>
                        <artifactId>git-commit-id-plugin</artifactId>
                        <version>2.2.4</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>revision</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <failOnNoGitDirectory>false</failOnNoGitDirectory>
                            <generateGitPropertiesFile>true</generateGitPropertiesFile>
                            <includeOnlyProperties>
                                <includeOnlyProperty>^git.commit.id.abbrev$</includeOnlyProperty>
                                <includeOnlyProperty>^git.commit.id.describe$</includeOnlyProperty>
                                <includeOnlyProperty>^git.branch$</includeOnlyProperty>
                            </includeOnlyProperties>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
            <properties>
                <!-- default Spring profiles -->
                <spring.profiles.active>prod${r'${profile.swagger}'}${r'${profile.no-liquibase}'}</spring.profiles.active>
            </properties>
        </profile>
    </profiles>


</project>