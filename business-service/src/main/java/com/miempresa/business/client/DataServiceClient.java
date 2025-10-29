// src/main/java/com/miempresa/business/client/DataServiceClient.java
package main.java.com.miempresa.business.client;

import com.miempresa.business.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
    name = "data-service",
    url = "${data-service.base-url}",
    configuration = DataServiceFeignConfig.class
)
public interface DataServiceClient {

  // Productos
  @GetMapping("/data/productos")
  List<ProductoDTO> listarProductos();

  @GetMapping("/data/productos/{id}")
  ProductoDTO obtenerProducto(@PathVariable Long id);

  @PostMapping("/data/productos")
  ProductoDTO crearProducto(@RequestBody ProductoRequest request);

  @PutMapping("/data/productos/{id}")
  ProductoDTO actualizarProducto(@PathVariable Long id, @RequestBody ProductoRequest request);

  @DeleteMapping("/data/productos/{id}")
  void eliminarProducto(@PathVariable Long id);

  // Categorías
  @GetMapping("/data/categorias")
  List<CategoriaDTO> listarCategorias();

  @GetMapping("/data/categorias/{id}")
  CategoriaDTO obtenerCategoria(@PathVariable Long id);

  // Inventario por producto
  @GetMapping("/data/inventario/{productoId}")
  InventarioDTO obtenerInventario(@PathVariable Long productoId);
}

