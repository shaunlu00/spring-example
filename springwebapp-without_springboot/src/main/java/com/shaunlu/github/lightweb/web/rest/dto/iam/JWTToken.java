package com.shaunlu.github.lightweb.web.rest.dto.iam;


public class JWTToken {

    public JWTToken(){}

    public JWTToken(String token){
        this.token = token;
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
