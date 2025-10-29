package com.miempresa.data_service.model;
        
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos", indexes = {
        @Index(name = "idx_producto_nombre", columnList = "nombre")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal precio;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer stock;

    // Un producto pertenece a una categoría
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Categoria categoria;

    // Un producto tiene un inventario (1:1)
    // Mapeado desde Inventario con FK única a Producto
    @OneToOne(mappedBy = "producto",
              cascade = CascadeType.ALL,
              orphanRemoval = true,
              fetch = FetchType.LAZY)
    private Inventario inventario;

    // Helper para mantener 1:1
    public void setInventario(Inventario inv) {
        if (inv != null) inv.setProducto(this);
        this.inventario = inv;
    }
}

