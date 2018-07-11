package com.shaunlu.springexample.test;

import com.shaunlu.springexample.Application;
import com.shaunlu.springexample.model.a.User;
import com.shaunlu.springexample.model.b.AppPermission;
import com.shaunlu.springexample.service.DeclarativeTransactionService;
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
public class DeclarativeTransactionTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeclarativeTransactionService transactionService;

    @Test
    public void test_createUser(){
        User user = transactionService.createNewUser("John", "john@example.com");
        assertNotNull(transactionService.findUserByName(user.getUname()));
    }

    @Test
    public void test_createUserWithRollback(){
        try {
            transactionService.createNewUserWithException("Peter", "peter@example.com");
        } catch (RuntimeException e) {
            logger.error("create new user error", e);
        }
        assertNull(transactionService.findUserByName("Peter"));
    }

    @Test
    public void test_createPermission(){
        AppPermission appPermission = transactionService.createNewPermission("John", "Read", true);
        assertNotNull(transactionService.findPermissionByUserId("John"));
    }

    @Test
    public void test_createPermissionWithException(){
        try {
            transactionService.createNewPermission("Peter", null, true);
        } catch (Exception e) {
            logger.error("create new permission error", e);
        }
        assertNull(transactionService.findPermissionByUserId("Peter"));
    }
}
