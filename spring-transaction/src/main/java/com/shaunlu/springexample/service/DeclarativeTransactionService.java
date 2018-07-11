package com.shaunlu.springexample.service;

import com.shaunlu.springexample.model.a.User;
import com.shaunlu.springexample.model.b.AppPermission;
import com.shaunlu.springexample.repository.a.UserRepository;
import com.shaunlu.springexample.repository.b.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeclarativeTransactionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional(transactionManager = "aTransactionManager")
    public User createNewUser(String userName, String email) {
        User user = new User(userName, email);
        userRepository.save(user);
        return user;
    }

    @Transactional(transactionManager = "aTransactionManager")
    public void createNewUserWithException(String userName, String email) {
        User user1 = new User(userName, email);
        User user2 = new User(userName, email);
        try {
            userRepository.save(user1);
            userRepository.save(user2);
        } catch (Exception e) {
            logger.error("create new user error", e);
            throw new RuntimeException("create new user error");
        }
    }

    public User findUserByName(String userName) {
        return userRepository.findOneByUname(userName);
    }

    @Transactional(transactionManager = "bTransactionManager")
    public AppPermission createNewPermission(String userId, String operation, Boolean isAllowed) {
        AppPermission permission = new AppPermission(userId, operation, isAllowed);
        permissionRepository.save(permission);
        return permission;
    }

    public AppPermission findPermissionByUserId(String userId) {
        return permissionRepository.findOneByUserId(userId);
    }

}
