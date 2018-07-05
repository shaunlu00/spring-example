package ${package}.repository;

import ${package}.domain.UserRoleView;
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
        String queryJPQL = "SELECT new ${package}.domain.UserRoleView("
                + "u.userId, u.userName, r.roleName, r.rolePermission) "
                + "from ${package}.domain.User u "
                + "left join ${package}.domain.UserRole urole on u.userId = urole.userId "
                + "left join ${package}.domain.Role r on urole.roleUid = r.uid "
                + "where r.roleName = :role_name";
        TypedQuery query = entityManager.createQuery(queryJPQL, UserRoleView.class);
        query.setParameter("role_name", roleName);
        // get pageable result
        query.setFirstResult(page.getPageSize() * page.getPageNumber());
        query.setMaxResults(page.getPageSize());
        return query.getResultList();
    }
}
