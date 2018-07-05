package com.shaunlu.github.lightweb.service;

import com.shaunlu.github.lightweb.domain.shopping.Computer;
import com.shaunlu.github.lightweb.domain.shopping.Order;
import com.shaunlu.github.lightweb.domain.shopping.OrderItem;
import com.shaunlu.github.lightweb.repository.ComputerRepository;
import com.shaunlu.github.lightweb.repository.OrderItemRepository;
import com.shaunlu.github.lightweb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@Transactional
public class ShoppingService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Computer computer, Integer number){
        String orderId = UUID.randomUUID().toString();
        Order order = new Order();
        order.setId(orderId);
        orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setComputer(computer);
        orderItem.setNumber(number);
        orderItem.setPrice(new Double(11010));
        orderItem.setOrderId(orderId);
        orderItemRepository.save(orderItem);
        return order;
    }

    public Order findOrderById(String orderId){
        Order order = orderRepository.findOneById(orderId);
        // to lazy fetch objects (in one to many relationships), Collection mush be iterated explicitly
        for(OrderItem item : order.getItems());
        return order;
    }
}
