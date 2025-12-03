package com.roy.similar_products.domain.error;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(String productId) {
    super("Producto no encontrado" + productId);
  }

}
