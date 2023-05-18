package com.polarbookshop.orderservice.order.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polarbookshop.orderservice.order.domain.entity.Order;
import com.polarbookshop.orderservice.order.domain.service.OrderService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping
    public Flux<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    
    @PostMapping
    public Mono<Order> submitOrder(@RequestBody @Valid OrderRequest orderRequest){
        return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
    }
}
