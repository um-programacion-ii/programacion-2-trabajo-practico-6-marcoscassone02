package com.miempresa.data_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventarios")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Inventario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Un inventario apunta a un producto con FK única (1:1)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_inventario_producto"))
    private Producto producto;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull @Min(0)
    @Column(nullable = false)
    private Integer stockMinimo;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    @PrePersist @PreUpdate
    void touch() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}

