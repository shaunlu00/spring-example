package ${package}.test.rest.web;


import ${package}.Application;
import ${package}.rest.security.JWTFilter;
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
public class UserResourceTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JWTFilter jwtFilter;


    private String access_token;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(jwtFilter)
                .build();

        ResultActions resultActions = mvc.perform(
                post("/web-api/auth/login")
                        .param("user_name","peter")
                        .param("password", "peter")
                        .contentType(MediaType.APPLICATION_JSON));
        String token = resultActions.andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testFindAllUsers() throws Exception {
        ResultActions resultActions = mvc.perform(
                post("/web-api/user/findall?size=5&page=0")
                        .header(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + this.access_token)
                        .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());
    }
}
