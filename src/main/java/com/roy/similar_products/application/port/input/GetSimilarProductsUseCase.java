package com.roy.similar_products.application.port.input;

import java.util.List;

import com.roy.similar_products.domain.models.Product;

public interface GetSimilarProductsUseCase {

  List<Product> getSimilarProducts(String productId);
}