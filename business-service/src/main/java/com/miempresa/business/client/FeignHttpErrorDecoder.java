// src/main/java/com/miempresa/business/client/FeignHttpErrorDecoder.java
package main.java.com.miempresa.business.client;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignHttpErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String methodKey, Response response) {
    int status = response.status();
    return switch (status) {
      case 400 -> new IllegalArgumentException("Solicitud inválida al data-service");
      case 404 -> new RuntimeException("Recurso no encontrado en data-service");
      case 409 -> new IllegalStateException("Conflicto de datos en data-service");
      case 503 -> new RuntimeException("data-service no disponible");
      default -> new RuntimeException("Error remoto (" + status + ") en data-service");
    };
  }
}

