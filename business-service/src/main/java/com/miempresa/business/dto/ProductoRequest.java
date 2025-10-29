// src/main/java/com/miempresa/business/dto/ProductoRequest.java
package main.java.com.miempresa.business.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoRequest {
  @NotBlank private String nombre;
  private String descripcion;
  @NotNull @DecimalMin("0.0") private BigDecimal precio;
  @NotNull private Long categoriaId;
  @NotNull @Min(0) private Integer stock;
}
