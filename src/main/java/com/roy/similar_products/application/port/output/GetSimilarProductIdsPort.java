package com.roy.similar_products.application.port.output;

import java.util.List;

public interface GetSimilarProductIdsPort {

  List<String> getSimilarProductIds(String productId);
}
