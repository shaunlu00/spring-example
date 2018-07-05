package com.shaunlu.github.lightweb.test.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaunlu.github.lightweb.config.APPConfiguration;
import com.shaunlu.github.lightweb.config.security.AuthConstants;
import com.shaunlu.github.lightweb.domain.shopping.Computer;
import com.shaunlu.github.lightweb.test.TestBase;
import com.shaunlu.github.lightweb.web.rest.dto.iam.JWTToken;
import com.shaunlu.github.lightweb.web.rest.dto.iam.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


public class ControllerTest extends TestBase{

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        // MockMvc will mock servlet, not servlet container. So it will not read WebApplicationInitializer implementation
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username="Peter",roles={"USER","ADMIN"})
    // The following test will be run as a user with the username "Peter", and the roles "ROLE_USER, ROLE_ADMIN"
    public void testShoppingController() throws Exception{
        MvcResult result = mockMvc.perform(post("/api/order/computer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        Computer computer = mapper.readValue(result.getResponse().getContentAsString(), Computer.class);
        Assert.assertNotNull(computer);
        Assert.assertEquals("IBM_X911QZTH",computer.getModel());
    }

    @Test
    public void testUnauthorizedRequest() throws Exception{
        mockMvc.perform(post("/api/order/computer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testJWTFilter() throws Exception{
        // perform login
        User user = new User();
        user.setLogin("aaa");
        user.setPassword("aaa");
        MvcResult loginResult = mockMvc.perform(post("/api/login")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        JWTToken jwtToken = mapper.readValue(loginResult.getResponse().getContentAsString(), JWTToken.class);
        Assert.assertNotNull(jwtToken);

        //perform further request
        String authHeader = "Bearer " + jwtToken.getToken();
        MvcResult  shoppingResult = mockMvc.perform(post("/api/order/computer")
                .header(AuthConstants.AUTHORIZATION_HEADER, authHeader)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        Computer computer = mapper.readValue(shoppingResult.getResponse().getContentAsString(), Computer.class);
        Assert.assertNotNull(computer);
        Assert.assertEquals("IBM_X911QZTH",computer.getModel());


    }
}
