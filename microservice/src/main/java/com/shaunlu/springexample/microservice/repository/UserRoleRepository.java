package com.shaunlu.springexample.microservice.repository;
import com.shaunlu.springexample.microservice.domain.UserRoleView;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserRoleRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<UserRoleView> searchUsers(String roleName, Pageable page) {
        String queryJPQL = "SELECT new com.shaunlu.springexample.microservice.domain.UserRoleView("
                + "u.userId, u.userName, r.roleName, r.rolePermission) "
                + "from com.shaunlu.springexample.microservice.domain.User u "
                + "left join com.shaunlu.springexample.microservice.domain.UserRole urole on u.userId = urole.userId "
                + "left join com.shaunlu.springexample.microservice.domain.Role r on urole.roleUid = r.uid "
                + "where r.roleName = :role_name";
        TypedQuery query = entityManager.createQuery(queryJPQL, UserRoleView.class);
        query.setParameter("role_name", roleName);
        // get pageable result
        query.setFirstResult(page.getPageSize() * page.getPageNumber());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }
}
