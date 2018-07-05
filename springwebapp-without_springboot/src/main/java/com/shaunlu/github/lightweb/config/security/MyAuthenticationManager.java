package com.shaunlu.github.lightweb.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MyAuthenticationManager implements AuthenticationManager{

    // used by login method
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();
        // authenticate through central server
        // ...
        Boolean authenticationResult = true;
        if (authenticationResult) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(AuthConstants.ROLE_USER));
            return new UsernamePasswordAuthenticationToken(userId, "", authorities);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
