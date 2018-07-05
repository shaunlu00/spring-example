package com.shaunlu.github.lightweb.repository;

import com.shaunlu.github.lightweb.domain.shopping.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shaun on 17-11-27.
 */
public interface ComputerRepository extends JpaRepository<Computer, Long>{

    Computer findOneByModel(String model);
}
