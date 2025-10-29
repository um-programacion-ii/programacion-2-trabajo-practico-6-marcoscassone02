// src/main/java/com/miempresa/business/dto/ProductoDTO.java
package main.java.com.miempresa.business.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoDTO {
  private Long id;
  private String nombre;
  private String descripcion;
  private BigDecimal precio;
  private Long categoriaId;
  private Integer stock;
}

