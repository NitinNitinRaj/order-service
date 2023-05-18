package com.polarbookshop.orderservice.order.domain.service;

import com.polarbookshop.orderservice.order.domain.entity.Order;

import reactor.core.publisher.Flux;

public interface OrderService {
    public Flux<Order> getAllOrders();
}
