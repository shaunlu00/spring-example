package com.shaunlu.github.lightweb.repository;

import com.shaunlu.github.lightweb.domain.shopping.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, String> {

    Order findOneById(String id);
}
