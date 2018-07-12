package com.shaunlu.springexample.microservice.rest.web;


import com.shaunlu.springexample.microservice.rest.security.AuthoritiesConstants;
import com.shaunlu.springexample.microservice.rest.security.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/web-api/auth")
public class AuthenticationResource {

    private final TokenProvider tokenProvider;

    public AuthenticationResource(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> login(HttpServletRequest request) {
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        if ("peter".equals(userName) && "peter".equals(password)) {
            String token = tokenProvider.createToken(userName, AuthoritiesConstants.USER);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
