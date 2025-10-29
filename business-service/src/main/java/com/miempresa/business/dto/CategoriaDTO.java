// src/main/java/com/miempresa/business/dto/CategoriaDTO.java
package main.java.com.miempresa.business.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoriaDTO {
  private Long id;
  private String nombre;
  private String descripcion;
}
