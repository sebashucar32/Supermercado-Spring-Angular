package com.sebastian.supermercado.app.models.services;

import java.util.List;

import com.sebastian.supermercado.app.dto.ProductoDto;

public interface IProductoService {
	public List<ProductoDto> buscarTodos();
	public void guardar(ProductoDto producto);
	public ProductoDto buscarUno(Long id);
	public void eliminar(Long id);
}
