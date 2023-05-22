package com.polarbookshop.orderservice.order.domain.service.impl;

import com.polarbookshop.orderservice.book.Book;
import com.polarbookshop.orderservice.book.BookClient;
import com.polarbookshop.orderservice.order.domain.entity.Order;
import com.polarbookshop.orderservice.order.domain.entity.OrderStatus;
import com.polarbookshop.orderservice.order.domain.repositories.OrderRepository;
import com.polarbookshop.orderservice.order.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final BookClient bookClient;

  public OrderServiceImpl(
    OrderRepository orderRepository,
    BookClient bookClient
  ) {
    this.orderRepository = orderRepository;
    this.bookClient = bookClient;
  }

  @Override
  public Flux<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Mono<Order> submitOrder(String isbn, int quantity) {
    // return Mono
    //   .just(buildRejectedOrder(isbn, quantity))
    //   .flatMap(orderRepository::save);

    return bookClient
      .getBookByIsbn(isbn)
      .map(book -> buildAcceptedOrder(book, quantity))
      .defaultIfEmpty(buildRejectedOrder(isbn, quantity))
      .flatMap(orderRepository::save);
  }

  public static Order buildAcceptedOrder(Book book, int quantity) {
    return Order.of(
      book.isbn(),
      book.title(),
      book.price(),
      quantity,
      OrderStatus.ACCEPTED
    );
  }

  public static Order buildRejectedOrder(String isbn, int quantity) {
    return Order.of(isbn, null, null, quantity, OrderStatus.REJECTED);
  }
}
