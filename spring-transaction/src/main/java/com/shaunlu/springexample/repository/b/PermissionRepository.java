package com.shaunlu.springexample.repository.b;

import com.shaunlu.springexample.model.b.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<AppPermission, Long> {

    AppPermission findOneById(Long id);

    AppPermission findOneByUserId(String userId);
}
