package com.polarbookshop.orderservice.book;

import java.io.IOException;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.reactive.function.client.WebClient;

public class BookClientTest {

  private BookClient bookClient;
  private MockWebServer mockWebServer;

  @BeforeEach
  void setup() throws IOException {
    this.mockWebServer = new MockWebServer();
    this.mockWebServer.start();
    var webClient = WebClient
      .builder()
      .baseUrl(mockWebServer.url("/").uri().toString())
      .build();
    this.bookClient = new BookClient(webClient);
  }

  @AfterEach
  void clean() throws IOException {
    this.mockWebServer.shutdown();
  }
}
