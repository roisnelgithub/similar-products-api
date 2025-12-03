package com.roy.similar_products.application.port.output;

import com.roy.similar_products.domain.models.Product;

public interface GetProductPort {
  Product getProductById(String productId);
}
