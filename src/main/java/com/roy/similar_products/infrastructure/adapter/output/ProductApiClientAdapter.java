package com.roy.similar_products.infrastructure.adapter.output;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.roy.similar_products.application.port.output.GetProductPort;
import com.roy.similar_products.application.port.output.GetSimilarProductIdsPort;
import com.roy.similar_products.domain.error.ProductApiException;
import com.roy.similar_products.domain.error.ProductNotFoundException;
import com.roy.similar_products.domain.models.Product;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ProductDTO;
import com.roy.similar_products.infrastructure.adapter.input.http.mapper.ProductMapper;

@Component
public class ProductApiClientAdapter implements GetProductPort, GetSimilarProductIdsPort {

  private final WebClient webClient;
  private final ProductMapper mapper;

  public ProductApiClientAdapter(WebClient webClient, ProductMapper mapper) {
    this.webClient = webClient;
    this.mapper = mapper;
  }

  @Override
  public List<String> getSimilarProductIds(String productId) {
    try {
      return webClient.get()
          .uri("/product/{id}/similarids", productId)
          .retrieve()
          .bodyToMono(new ParameterizedTypeReference<List<String>>() {
          })
          .block();

    } catch (WebClientResponseException e) {
      if (e.getStatusCode().is5xxServerError()) {
        throw new ProductApiException("Servicio remoto falló al obtener IDs similares.", e);
      } else if (e.getStatusCode().value() == 404) {
        throw new ProductNotFoundException(productId);
      } else {
        throw new ProductApiException("Respuesta inesperada del servicio de IDs.", e);
      }

    } catch (Exception e) {
      throw new ProductApiException("Error inesperado al obtener los IDs similares.", e);
    }
  }

  @Override
  public Product getProductById(String productId) {
    try {
      ProductDTO dto = webClient.get()
          .uri("/product/{id}", productId)
          .retrieve()
          .bodyToMono(ProductDTO.class)
          .block();

      return mapper.toDomain(dto);

    } catch (WebClientResponseException e) {
      if (e.getStatusCode().is5xxServerError()) {
        throw new ProductApiException("Servicio remoto falló al obtener el producto.", e);
      } else if (e.getStatusCode().value() == 404) {
        throw new ProductNotFoundException(productId);
      } else {
        throw new ProductApiException("Respuesta inesperada del servicio de productos.", e);
      }
    } catch (Exception e) {
      throw new ProductApiException("Error inesperado del servicio de productos.", e);
    }
  }

}
