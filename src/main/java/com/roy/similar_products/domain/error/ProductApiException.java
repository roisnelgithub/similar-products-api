package com.roy.similar_products.domain.error;

public class ProductApiException extends RuntimeException {
  public ProductApiException(String message, Throwable cause) {
    super(message, cause);
  }
}