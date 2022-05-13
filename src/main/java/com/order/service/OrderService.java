package com.order.service;

import com.order.model.OrderDetail;
import com.order.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void add(OrderDetail orderDetail) {
        orderRepository.save(orderDetail);
    }

    public List<OrderDetail> getAll() {
        return orderRepository.findAll();
    }

    public OrderDetail getById(String id) {
        return orderRepository.findById(id).get();
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }
}
