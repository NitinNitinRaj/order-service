package com.polarbookshop.orderservice.order.domain.service.impl;

import com.polarbookshop.orderservice.order.domain.entity.Order;
import com.polarbookshop.orderservice.order.domain.repositories.OrderRepository;
import com.polarbookshop.orderservice.order.domain.service.OrderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

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
}
