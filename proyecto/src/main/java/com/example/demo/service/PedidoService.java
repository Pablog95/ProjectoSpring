package com.example.demo.service;

import com.example.demo.model.Carrito;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }


    public Pedido crearPedido(Pedido pedido) {
        double total = 0.0;

        for (Carrito pedidoProducto : pedido.getProductosCarrito()) {

            Producto producto = productoRepository.findById(pedidoProducto.getProducto().getId()).
                    orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int cantidad = pedidoProducto.getCantidad();

            if(producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente del producto: " +producto.getNombre());
            }

            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);

            pedidoProducto.setProducto(producto);
            pedidoProducto.setPedido(pedido);
            total += producto.getPrecio() * cantidad;
        }
        pedido.setTotal(total);
        pedido.setEstado(false);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

}
