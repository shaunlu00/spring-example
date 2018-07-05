package com.shaunlu.github.lightweb.web.rest;

import com.shaunlu.github.lightweb.config.security.AuthConstants;
import com.shaunlu.github.lightweb.config.security.JWT.TokenProvider;
import com.shaunlu.github.lightweb.domain.shopping.Computer;
import com.shaunlu.github.lightweb.repository.ComputerRepository;
import com.shaunlu.github.lightweb.web.rest.dto.iam.JWTToken;
import com.shaunlu.github.lightweb.web.rest.dto.iam.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/api/order")
public class ShoppingController {

    @Autowired
    private ComputerRepository computerRepository;

    @RequestMapping(value = "/computer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getComputer(){
        Computer computer = computerRepository.findAll().get(0);
        return new ResponseEntity<Computer>(computer, HttpStatus.OK);
    }



}
