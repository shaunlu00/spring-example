package com.shaunlu.springexample.microservice.repository;
import com.shaunlu.springexample.microservice.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUserId(String userId);

    Page<User> findAll(Pageable page);
}
