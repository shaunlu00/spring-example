package com.shaunlu.github.lightweb.config.security.JWT;

import com.shaunlu.github.lightweb.config.property.ApplicationProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TokenProvider {

    Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    @Autowired
    ApplicationProperty applicationProperty;

    private String secretKey;

    private long tokenValidityInSeconds;

    private long tokenValidityInSecondsForRememberMe;

    private String AUTHORITIES_KEY="auth";

    @PostConstruct
    public void init() {
        this.secretKey = applicationProperty.getWebSecurity().getJwtSecretKey();
        this.tokenValidityInSeconds = applicationProperty.getWebSecurity().getTokenValidityInSeconds();
        this.tokenValidityInSecondsForRememberMe = applicationProperty.getWebSecurity().getTokenValidityInSecondsForRememberMe();
    }

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = null;
        for(GrantedAuthority auth: authentication.getAuthorities()){
            if (null == authorities){
                authorities = auth.getAuthority();
            } else {
                authorities = authorities + "," + auth.getAuthority();
            }
        }

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInSecondsForRememberMe * 1000);
        } else {
            validity = new Date(now + this.tokenValidityInSeconds * 1000);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(String auth : claims.get(AUTHORITIES_KEY).toString().split(",")){
            authorities.add(new SimpleGrantedAuthority(auth));
        }

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.info("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }
}
