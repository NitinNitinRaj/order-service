package com.polarbookshop.orderservice.order.domain.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.polarbookshop.orderservice.order.domain.entity.Order;

public interface OrderRepository extends ReactiveCrudRepository<Order,Long>{
    
}
