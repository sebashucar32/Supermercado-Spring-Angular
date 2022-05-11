package com.sebastian.supermercado.app.models.services;

import org.springframework.http.ResponseEntity;

import com.sebastian.supermercado.app.dto.ProductoDto;

public interface IProductoService {
	public ResponseEntity<?> buscarProductos();
	public ResponseEntity<?> mostrarProducto(Long id);
	public ResponseEntity<?> crearProducto(ProductoDto producto);
	public ResponseEntity<?> guardarProducto(ProductoDto producto, Long id);
	public ResponseEntity<?> eliminarProducto(Long id);
}
