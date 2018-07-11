package com.shaunlu.springexample.test;

import com.shaunlu.springexample.Application;
import com.shaunlu.springexample.model.a.User;
import com.shaunlu.springexample.service.ProgrammaticTransctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProgrammaticTransactionTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProgrammaticTransctionService transctionService;


    @Test
    public void test_createUser(){
        User user = transctionService.createNewUser("Micheal", "micheal@example.com");
        assertNotNull(transctionService.findUserByName(user.getUname()));
    }

    @Test
    public void test_createUserWithException(){
        transctionService.createNewUserWithException("Steve", "steve@example.com");
        assertNull(transctionService.findUserByName("Steven"));
    }

    @Test
    public void test_createUserAndPermission() {
        transctionService.createUserAndPermission("Badman", "badman@example", "read", true);
        assertNotNull(transctionService.findUserByName("Badman"));
    }

    @Test
    public void test_createUserAndPermissionWithException() {
        transctionService.createUserAndPermission("Superman", "superman@example", null, true);
        assertNull(transctionService.findUserByName("Superman"));
    }
}
