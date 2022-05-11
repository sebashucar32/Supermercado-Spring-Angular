package com.sebastian.supermercado.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.supermercado.app.dto.ProductoDto;
import com.sebastian.supermercado.app.models.services.IProductoService;

@RestController
@RequestMapping("/api/supermercado")
@CrossOrigin(origins={"http://localhost:4200"})
public class ProductoController {
	@Autowired
	private IProductoService productoService;
	
	@GetMapping("/mostrarproductos")
	public ResponseEntity<?> listarProductos() {
		ResponseEntity<?> productosResponse = productoService.buscarProductos();
		return productosResponse;
	}
	
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> mostrarProducto(@PathVariable Long id) {
		ResponseEntity<?> productosResponse = productoService.mostrarProducto(id);
		return productosResponse;
	}
	
	@PostMapping("/producto")
	public ResponseEntity<?> crearProducto(@RequestBody ProductoDto producto) {
		ResponseEntity<?> productosResponse = productoService.crearProducto(producto);
		return productosResponse;
	}
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> actualizarProducto(@RequestBody ProductoDto producto, @PathVariable Long id) {
		ResponseEntity<?> productosResponse = productoService.guardarProducto(producto, id);
		return productosResponse;
	}
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
		ResponseEntity<?> productosResponse = productoService.eliminarProducto(id);
		return productosResponse;
	}
}
