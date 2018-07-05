package com.shaunlu.github.lightweb.test.dal;

import com.shaunlu.github.lightweb.domain.shopping.Computer;
import com.shaunlu.github.lightweb.domain.shopping.Order;
import com.shaunlu.github.lightweb.repository.ComputerRepository;
import com.shaunlu.github.lightweb.service.ShoppingService;
import com.shaunlu.github.lightweb.test.TestBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;


public class DALTest extends TestBase {

    @Autowired
    private ShoppingService shoppingService;

    @Autowired
    private ComputerRepository computerRepository;

    private String currentUser = "John";

    private String computer_model = "IBM_X911QZTH";

    @Before
    public void setUp(){
        // add authenticated user in Spring security context, which is needed by auditing
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(currentUser, "", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    @Test
    public void testSaveAndQuery(){
        Computer computer = computerRepository.findOneByModel(computer_model);
        Assert.assertNotNull(computer);

        Order order = shoppingService.createOrder(computer, 10);
        Order result = shoppingService.findOrderById(order.getId());
        Assert.assertEquals(computer_model, result.getItems().iterator().next().getComputer().getModel());
    }

    @Test
    public void testAuditing() {
        Computer computer = computerRepository.findOneByModel(computer_model);
        Assert.assertNotNull(computer);

        Order order = shoppingService.createOrder(computer, 10);
        Order result = shoppingService.findOrderById(order.getId());
        Assert.assertEquals(currentUser, result.getCreatedBy());
    }
}
