package com.sebastian.supermercado.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public List<ProductoDto> listarProductos() {
		return productoService.buscarTodos();
	}
	
	@GetMapping("/producto/{id}")
	public ProductoDto mostrarProducto(@PathVariable Long id) {
		return productoService.buscarUno(id);
	}
	
	@PostMapping("/producto")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductoDto crearProducto(@RequestBody ProductoDto producto) {
		productoService.guardar(producto);
		return producto;
	}
	
	@PutMapping("/producto/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductoDto actualizarProducto(@RequestBody ProductoDto producto, @PathVariable Long id) {
		ProductoDto productoActual = productoService.buscarUno(id);
		
		productoActual.setNombre(producto.getNombre());
		productoActual.setCategoria(producto.getCategoria());
		productoActual.setFechaProducto(producto.getFechaProducto());
		productoActual.setPrecio(producto.getPrecio());
		productoService.guardar(productoActual);
		
		return productoActual;
	}
	
	@DeleteMapping("/producto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrarProducto(@PathVariable Long id) {
		productoService.eliminar(id);
	}
}
