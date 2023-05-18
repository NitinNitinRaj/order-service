package com.polarbookshop.orderservice.order.domain.service.impl;

import com.polarbookshop.orderservice.order.domain.entity.Order;
import com.polarbookshop.orderservice.order.domain.entity.OrderStatus;
import com.polarbookshop.orderservice.order.domain.repositories.OrderRepository;
import com.polarbookshop.orderservice.order.domain.service.OrderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Mono<Order> submitOrder(String isbn, int quantity) {
    return Mono.just(buildRejectedOrder(isbn, quantity)).flatMap(orderRepository::save);
  }

  public static Order buildRejectedOrder(String isbn, int quantity) {
    return Order.of(isbn, null, null, quantity, OrderStatus.REJECTED);
  }
}
