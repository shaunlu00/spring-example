package com.shaunlu.github.lightweb.config.security;

import com.shaunlu.github.lightweb.config.security.JWT.JWTFilter;
import com.shaunlu.github.lightweb.config.security.JWT.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//enable annotation-based security
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return new MyAuthenticationManager();
    }

    // expose static files
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/bower_components/**")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html")
                .antMatchers("/test/**")
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedEntryPoint)
        .and()
            .csrf().disable().headers().frameOptions().disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/login").permitAll()
            .antMatchers("/api/**").authenticated()
            .antMatchers("/api/admin/**").hasAuthority(AuthConstants.ROLE_ADMIN);

        // add jwt filter
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        // UsernamePasswordAuthenticationFilter is a default filter which will filter all /login path request
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
