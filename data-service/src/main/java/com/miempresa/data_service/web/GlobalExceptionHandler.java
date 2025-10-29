// src/main/java/com/miempresa/data/web/GlobalExceptionHandler.java
package com.miempresa.data_service.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(Map.of(
        "error","VALIDATION_ERROR",
        "details", ex.getBindingResult().getFieldErrors().stream()
            .map(e -> Map.of("field", e.getField(), "msg", e.getDefaultMessage()))
            .toList()
    ));
  }
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> conflict(DataIntegrityViolationException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","DATA_INTEGRITY"));
  }
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> generic(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","NOT_FOUND","msg", ex.getMessage()));
  }
}

