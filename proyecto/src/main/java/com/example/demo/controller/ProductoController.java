package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable Long id) {
        return productoService.buscarProductoPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @PatchMapping("/{id}/precio")
    public String actualizarPrecio(@PathVariable Long id, @RequestParam double precio) {
        return productoService.actualizarPrecioProducto(id, precio);
    }

    @PatchMapping("/{id}/stock")
    public String actualizarStock(@PathVariable Long id, @RequestParam int stock) {
        return productoService.actualizarStockProducto(id, stock);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        return productoService.eliminarProducto(id);
    }


}
