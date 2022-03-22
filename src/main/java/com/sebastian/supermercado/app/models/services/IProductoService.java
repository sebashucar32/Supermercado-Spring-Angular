package com.sebastian.supermercado.app.models.services;

import org.springframework.http.ResponseEntity;

import com.sebastian.supermercado.app.dto.ProductoDto;
import com.sebastian.supermercado.app.response.ProductoResponse;

public interface IProductoService {
	public ResponseEntity<ProductoResponse> buscarProductos();
	public ResponseEntity<ProductoResponse> mostrarProducto(Long id);
	public ResponseEntity<ProductoResponse> crearProducto(ProductoDto producto);
	public ResponseEntity<ProductoResponse> guardarProducto(ProductoDto producto, Long id);
	public ResponseEntity<ProductoResponse> eliminarProducto(Long id);
}
