
package com.miempresa.data_service.model;
        
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categorias", indexes = {
        @Index(name = "idx_categoria_nombre", columnList = "nombre", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 120)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    // Una categoría tiene muchos productos
    @OneToMany(mappedBy = "categoria",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Producto> productos = new LinkedHashSet<>();

    // Helpers para mantener la bidireccionalidad
    public void addProducto(Producto p) {
        productos.add(p);
        p.setCategoria(this);
    }
    public void removeProducto(Producto p) {
        productos.remove(p);
        p.setCategoria(null);
    }
}
