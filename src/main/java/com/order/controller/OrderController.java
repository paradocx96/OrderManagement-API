package com.order.controller;

import com.order.model.OrderDetail;
import com.order.model.OrderRequest;
import com.order.service.OrderItemService;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/api/product/buyer")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/place-order")
    public void add(@RequestBody OrderRequest orderRequest) {
        orderService.add(orderRequest.getOrderDetail());
        orderItemService.updateProductQtyAndSaveOrderItems(orderRequest.getOrderItems());
    }

    @PostMapping("/add-order")
    public void add(@RequestBody OrderDetail orderDetail) {
        orderService.add(orderDetail);
    }

    @GetMapping("/get-order")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDetail> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/get-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDetail getById(@PathVariable String id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/delete-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable String id) {
        orderService.deleteById(id);
    }
}
