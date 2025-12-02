package com.roy.similar_products.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  WebClient webClient(WebClient.Builder builder) {
    return builder.baseUrl("http://localhost:3001").defaultHeader("Content-Type", "application/json").build();
  }
}
