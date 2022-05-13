package com.order.model;

import java.util.Arrays;

public class OrderRequest {

    private OrderDetail orderDetail;
    private OrderItem[]  orderItems;

    public OrderRequest() {
    }

    public OrderRequest(OrderDetail orderDetail, OrderItem[] orderItems) {
        this.orderDetail = orderDetail;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderDetail=" + orderDetail +
                ", orderItems=" + Arrays.toString(orderItems) +
                '}';
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(OrderItem[] orderItems) {
        this.orderItems = orderItems;
    }
}
