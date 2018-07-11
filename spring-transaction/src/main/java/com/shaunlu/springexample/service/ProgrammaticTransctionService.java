package com.shaunlu.springexample.service;

import com.shaunlu.springexample.model.a.User;
import com.shaunlu.springexample.model.b.AppPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ProgrammaticTransctionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("aEntityManager")
    private EntityManagerFactory aEmf;

    @Autowired
    @Qualifier("bEntityManager")
    private EntityManagerFactory bEmf;

    public User createNewUser(String userName, String email) {
        EntityManager entityManager = aEmf.createEntityManager();
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        User user = new User(userName, email);
        entityManager.persist(user);
        t.commit();
        return user;
    }

    public void createNewUserWithException(String userName, String email) {
        EntityManager entityManager = aEmf.createEntityManager();
        EntityTransaction t = entityManager.getTransaction();
        t.begin();
        try {
            User user1 = new User(userName, email);
            User user2 = new User(userName, email);
            entityManager.persist(user1);
            entityManager.persist(user2);
            t.commit();
        } catch (Exception e) {
            logger.error("create new user error", e);
            t.rollback();
        }
    }

    public User findUserByName (String userName) {
        User user = null;
        EntityManager entityManager = aEmf.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM com.shaunlu.springexample.model.a.User u WHERE u.uname = :uname", User.class);
        query.setParameter("uname", userName);
        List<User> results = query.getResultList();
        if (null != results && 0 != results.size()) {
            user = results.get(0);
        }
        return user;
    }

    public void createUserAndPermission(String userName, String email, String operation, Boolean isAllowed) {
        User user = new User(userName, email);
        AppPermission permission = new AppPermission(userName, operation, isAllowed);
        EntityManager aEm = aEmf.createEntityManager();
        EntityTransaction aTransaction = aEm.getTransaction();
        EntityManager bEm = bEmf.createEntityManager();
        EntityTransaction bTransaction = bEm.getTransaction();
        try {
            aTransaction.begin();
            bTransaction.begin();
            aEm.persist(user);
            bEm.persist(permission);
            aTransaction.commit();
            bTransaction.commit();
        } catch (Exception e) {
            logger.error("create user and permission error", e);
            aTransaction.rollback();
            bTransaction.rollback();
        }
    }
}
