package com.roy.similar_products.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${product.service.base-url}")
  private String baseUrl;

  @Bean
  WebClient webClient(WebClient.Builder builder) {
    return builder.baseUrl(baseUrl).defaultHeader("Content-Type", "application/json").build();
  }
}
