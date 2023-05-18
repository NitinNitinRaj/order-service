package com.polarbookshop.orderservice.order.domain.service;

import com.polarbookshop.orderservice.order.domain.entity.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    public Flux<Order> getAllOrders();
    public Mono<Order> submitOrder(String isbn, int quantity);
}
