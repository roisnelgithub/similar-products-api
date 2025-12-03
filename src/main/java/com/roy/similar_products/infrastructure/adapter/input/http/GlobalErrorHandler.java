package com.roy.similar_products.infrastructure.adapter.input.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.roy.similar_products.domain.error.ProductApiException;
import com.roy.similar_products.infrastructure.adapter.input.http.dto.ErrorResponseDTO;
import com.roy.similar_products.domain.error.ProductNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleProductNotFound(ProductNotFoundException ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(ProductApiException.class)
  public ResponseEntity<ErrorResponseDTO> handleProductService(ProductApiException ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "Ocurrió un error inesperado",
        LocalDateTime.now());

    ex.printStackTrace();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
