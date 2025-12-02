package com.roy.similar_products.application.use_case;

import java.util.List;

import org.springframework.stereotype.Service;

import com.roy.similar_products.application.port.input.GetSimilarProductsUseCase;
import com.roy.similar_products.application.port.output.GetProductPort;
import com.roy.similar_products.application.port.output.GetSimilarProductIdsPort;
import com.roy.similar_products.domain.error.ProductNotFoundException;
import com.roy.similar_products.domain.models.Product;

@Service
public class GetSimilarProductsUseCaseImpl implements GetSimilarProductsUseCase {

  final GetSimilarProductIdsPort getSimilarProductIdsPort;
  final GetProductPort getProductPort;

  public GetSimilarProductsUseCaseImpl(
      GetSimilarProductIdsPort getSimilarProductIdsPort,
      GetProductPort getProductPort) {
    this.getSimilarProductIdsPort = getSimilarProductIdsPort;
    this.getProductPort = getProductPort;
  }

  @Override
  public List<Product> getSimilarProducts(String productId) {
    List<String> similarProductIds = getSimilarProductIdsPort.getSimilarProductIds(productId);

    if (similarProductIds == null) {
      throw new ProductNotFoundException(productId);
    }

    if (similarProductIds.isEmpty()) {
      return List.of();
    }

    return similarProductIds.stream()
        .map(this::getProductOrNull)
        .filter(product -> product != null)
        .toList();
  }

  private Product getProductOrNull(String productId) {
    return getProductPort.getProductById(productId);
  }

}
