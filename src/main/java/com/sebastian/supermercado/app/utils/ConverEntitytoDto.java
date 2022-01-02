package com.sebastian.supermercado.app.utils;

import org.springframework.stereotype.Component;

import com.sebastian.supermercado.app.dto.ProductoDto;
import com.sebastian.supermercado.app.models.entity.Producto;

@Component("converEntitytoDto")
public class ConverEntitytoDto {
	public ProductoDto converToDtoProducto(Producto producto) {
		ProductoDto productoDto = new ProductoDto();
		
		if(producto != null) {
			productoDto.setId(producto.getId());
			productoDto.setNombre(producto.getNombre());
			productoDto.setPrecio(producto.getPrecio());
			productoDto.setCategoria(producto.getCategoria());
			productoDto.setFechaProducto(producto.getFechaProducto());
		}
		
		return productoDto;
	}
	
	public Producto convertToEntityProducto(ProductoDto producto) {
		Producto productoEntity = new Producto();
		
		if(producto != null) {
			productoEntity.setId(producto.getId());
			productoEntity.setNombre(producto.getNombre());
			productoEntity.setCategoria(producto.getCategoria());
			productoEntity.setFechaProducto(producto.getFechaProducto());
			productoEntity.setPrecio(producto.getPrecio());
		}
		
		return productoEntity;
	}
}

