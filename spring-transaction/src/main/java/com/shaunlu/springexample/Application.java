package com.shaunlu.springexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.net.UnknownHostException;

//@EnableAutoConfiguration
@ComponentScan( basePackages = "com.shaunlu.springexample")
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }
}
