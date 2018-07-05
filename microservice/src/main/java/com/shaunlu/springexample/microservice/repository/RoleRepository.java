package com.shaunlu.springexample.microservice.repository;
import com.shaunlu.springexample.microservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
