package com.shaunlu.github.lightweb.repository;

import com.shaunlu.github.lightweb.domain.shopping.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shaun on 17-11-27.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

    List<OrderItem> findAllByOrderId(String orderId);
}
