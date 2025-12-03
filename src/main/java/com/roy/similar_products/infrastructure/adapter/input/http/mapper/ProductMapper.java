package com.roy.similar_products.infrastructure.adapter.input.http.mapper;

import org.springframework.stereotype.Component;

import com.roy.similar_products.domain.models.Product;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ProductDTO;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ProductResponseDTO;

@Component
public class ProductMapper {
  public Product toDomain(ProductDTO productDTO) {
    return new Product(
        productDTO.id(),
        productDTO.name(),
        productDTO.price(),
        productDTO.availability());
  }

  public ProductResponseDTO toDTO(Product product) {
    return new ProductResponseDTO(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getAvailability());
  }
}
