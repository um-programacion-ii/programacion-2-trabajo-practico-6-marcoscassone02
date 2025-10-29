// src/main/java/com/miempresa/business/dto/ProductoResponse.java
package main.java.com.miempresa.business.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoResponse {
  private Long id;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private String categoria;    // nombre legible
  private Integer stock;
  private boolean bajoStock;   // regla de negocio
}
