package com.polarbookshop.orderservice.book;

import java.time.Duration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Component
public class BookClient {

  private static final String BOOK_ROOT_API = "/api/v1/books/";
  private final WebClient webClient;

  public BookClient(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Book> getBookByIsbn(String isbn) {
    return webClient
      .get()
      .uri(BOOK_ROOT_API + isbn)
      .retrieve()
      .bodyToMono(Book.class)
      .timeout(Duration.ofSeconds(3), Mono.empty())
      .onErrorResume(WebClientResponseException.class, e -> Mono.empty())
      .retryWhen(Retry.backoff(3, Duration.ofMillis(100)))
      .onErrorResume(Exception.class, e -> Mono.empty());
  }
}
