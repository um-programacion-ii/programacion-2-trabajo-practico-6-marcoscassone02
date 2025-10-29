// src/main/java/com/miempresa/business/dto/InventarioDTO.java
package main.java.com.miempresa.business.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class InventarioDTO {
  private Long id;
  private Long productoId;
  private Integer cantidad;
  private Integer stockMinimo;
  private LocalDateTime fechaActualizacion;
}
