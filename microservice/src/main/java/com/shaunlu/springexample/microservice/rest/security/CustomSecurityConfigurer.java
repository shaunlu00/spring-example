package com.shaunlu.springexample.microservice.rest.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JWTFilter jwtFilter;

    CustomSecurityConfigurer(JWTFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
