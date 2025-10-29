// src/main/java/com/miempresa/data/controller/DataController.java
package com.miempresa.data_service.controller;

import com.miempresa.data_service.model.*;
import com.miempresa.data_service.repository.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

  private final ProductoRepository productoRepo;
  private final CategoriaRepository categoriaRepo;
  private final InventarioRepository inventarioRepo;

  // --- Productos ---
  @GetMapping("/productos")
  public List<Producto> productos() { return productoRepo.findAll(); }

  @GetMapping("/productos/{id}")
  public Producto producto(@PathVariable Long id) {
    return productoRepo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
  }

  @PostMapping("/productos")
  @ResponseStatus(HttpStatus.CREATED)
  public Producto crearProducto(@Valid @RequestBody Producto p) {
    return productoRepo.save(p);
  }

  @PutMapping("/productos/{id}")
  public Producto actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto p) {
    Producto e = productoRepo.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    e.setNombre(p.getNombre()); e.setDescripcion(p.getDescripcion()); e.setPrecio(p.getPrecio());
    e.setStock(p.getStock()); e.setCategoria(p.getCategoria());
    return productoRepo.save(e);
  }

  @DeleteMapping("/productos/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminarProducto(@PathVariable Long id) { productoRepo.deleteById(id); }

  // --- Categorías ---
  @GetMapping("/categorias")
  public List<Categoria> categorias() { return categoriaRepo.findAll(); }

  @GetMapping("/categorias/{id}")
  public Categoria categoria(@PathVariable Long id) {
    return categoriaRepo.findById(id).orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
  }

  @PostMapping("/categorias")
  @ResponseStatus(HttpStatus.CREATED)
  public Categoria crearCategoria(@Valid @RequestBody Categoria c) { return categoriaRepo.save(c); }

  // --- Inventario ---
  @GetMapping("/inventario/{productoId}")
  public Inventario inventario(@PathVariable Long productoId) {
    return inventarioRepo.findAll().stream()
        .filter(i -> i.getProducto().getId().equals(productoId))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
  }

  @PutMapping("/inventario/{productoId}")
  public Inventario actualizarInventario(@PathVariable Long productoId, @Valid @RequestBody Inventario body) {
    Inventario inv = inventario();
    inv.setCantidad(body.getCantidad());
    inv.setStockMinimo(body.getStockMinimo());
    return inventarioRepo.save(inv);
    // helper local
    Inventario inventario() {
      return inventarioRepo.findAll().stream()
          .filter(i -> i.getProducto().getId().equals(productoId))
          .findFirst()
          .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }
  }
}

