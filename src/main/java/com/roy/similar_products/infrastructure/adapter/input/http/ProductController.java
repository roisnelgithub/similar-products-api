package com.roy.similar_products.infrastructure.adapter.input.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roy.similar_products.application.use_case.GetSimilarProductsUseCaseImpl;
import com.roy.similar_products.domain.models.Product;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ProductResponseDTO;
import com.roy.similar_products.infrastructure.adapter.input.http.mapper.ProductMapper;

import jakarta.annotation.Nonnull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
public class ProductController {

  final GetSimilarProductsUseCaseImpl getSimilarProductsUseCase;
  final ProductMapper productMapper;

  public ProductController(
      GetSimilarProductsUseCaseImpl getSimilarProductsUseCase,
      ProductMapper productMapper) {
    this.getSimilarProductsUseCase = getSimilarProductsUseCase;
    this.productMapper = productMapper;
  }

  @GetMapping("/{productId}/similar")
  public ResponseEntity<List<ProductResponseDTO>> getMethodName(@PathVariable @Nonnull String productId) {
    List<Product> similarProducts = getSimilarProductsUseCase.getSimilarProducts(productId);
    return ResponseEntity.ok(similarProducts.stream()
        .map(productMapper::toDTO)
        .toList());
  }
}
