package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public String actualizarProducto(Long id, Producto producto) {
        return productoRepository.findById(id).map(p -> {
            p.setNombre(producto.getNombre());
            p.setPrecio(producto.getPrecio());
            productoRepository.save(p);
            return "Producto actualizado";
        }).orElse("Producto no encontrado");
    }

    public String eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return "Producto eliminado";
        }
        return "Producto no encontrado";
    }

    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> buscarProductoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre del producto no puede ser vacio");
        }
        return productoRepository.findByNombreContainingIgnoreCase(nombre.trim());
    }

    public String actualizarPrecioProducto(Long id, double precio) {
        return productoRepository.findById(id).map(producto -> {
            producto.setPrecio(precio);
            productoRepository.save(producto);
            return "Precio actualizado";
        }).orElse("Producto no encontrado");
    }

    public String actualizarStockProducto(Long id, Integer stock) {
        return productoRepository.findById(id).map(producto -> {
            producto.setStock(stock);
            productoRepository.save(producto);
            return "Stock actualizado";
        }).orElse("Producto no Encontrado");
    }


}