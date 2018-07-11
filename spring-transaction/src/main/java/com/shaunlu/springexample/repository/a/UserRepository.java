package com.shaunlu.springexample.repository.a;

import com.shaunlu.springexample.model.a.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUname(String userName);
}
