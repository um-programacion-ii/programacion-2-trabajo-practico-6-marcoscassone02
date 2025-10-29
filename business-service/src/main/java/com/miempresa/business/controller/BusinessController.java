// src/main/java/com/miempresa/business/controller/BusinessController.java
package main.java.com.miempresa.business.controller;

import com.miempresa.business.dto.*;
import com.miempresa.business.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BusinessController {

  private final ProductoBusinessService productoSvc;
  private final CategoriaBusinessService categoriaSvc;
  private final InventarioBusinessService inventarioSvc;

  // /api/productos
  @GetMapping("/productos")
  public List<ProductoResponse> listarProductos() { return productoSvc.listar(); }

  @GetMapping("/productos/{id}")
  public ProductoResponse producto(@PathVariable Long id) { return productoSvc.obtener(id); }

  @PostMapping("/productos")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductoResponse crear(@Valid @RequestBody ProductoRequest req) { return productoSvc.crear(req); }

  @PutMapping("/productos/{id}")
  public ProductoResponse actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest req) {
    return productoSvc.actualizar(id, req);
  }

  @DeleteMapping("/productos/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void eliminar(@PathVariable Long id) { productoSvc.eliminar(id); }

  // /api/categorias
  @GetMapping("/categorias")
  public List<CategoriaDTO> categorias() { return categoriaSvc.listar(); }

  @GetMapping("/categorias/{id}")
  public CategoriaDTO categoria(@PathVariable Long id) { return categoriaSvc.obtener(id); }

  // /api/reportes (ejemplo simple: inventario por producto)
  @GetMapping("/reportes/inventario/{productoId}")
  public InventarioDTO inventario(@PathVariable Long productoId) {
    return inventarioSvc.obtenerPorProducto(productoId);
  }
}

