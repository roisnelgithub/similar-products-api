package com.roy.similar_products.infrastructure.adapter.input.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roy.similar_products.application.use_case.GetSimilarProductsUseCaseImpl;
import com.roy.similar_products.domain.models.Product;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ProductResponseDTO;
import com.roy.similar_products.infrastructure.adapter.input.http.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/product")
@Tag(name = "Product API", description = "API para consultar productos similares a uno dado")
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
  @Operation(summary = "Obtener productos similares", description = "Devuelve una lista de productos similares al producto indicado por su ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de productos similares encontrada"),
      @ApiResponse(responseCode = "404", description = "Producto no encontrado")
  })
  public ResponseEntity<List<ProductResponseDTO>> getMethodName(
      @Parameter(description = "ID del producto", required = true) @PathVariable @Nonnull String productId) {
    List<Product> similarProducts = getSimilarProductsUseCase.getSimilarProducts(productId);
    return ResponseEntity.ok(similarProducts.stream()
        .map(productMapper::toDTO)
        .toList());
  }
}
