package com.sebastian.supermercado.app.models.services;

import java.util.List;

import com.sebastian.supermercado.app.models.entity.Producto;

public interface IProductoService {
	public List<Producto> buscarTodos();
	public void guardar(Producto producto);
	public Producto buscarUno(Long id);
	public void eliminar(Long id);
}
