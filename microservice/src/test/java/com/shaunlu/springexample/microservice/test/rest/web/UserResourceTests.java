package com.shaunlu.springexample.microservice.test.rest.web;


import com.shaunlu.springexample.microservice.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class UserResourceTests {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .addFilter(new JWTFilter())
                .build();
    }

    @Test
    public void testFindAllUsers() throws Exception {
        ResultActions resultActions = mvc.perform(
                post("/web-api/user/findall?size=5&page=0")
                        .header("abc", "abc")
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}
