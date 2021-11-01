package com.sebastian.supermercado.app.models.dao;

import java.util.List;

import com.sebastian.supermercado.app.models.entity.Producto;

public interface IProductoDao {
	public List<Producto> buscarTodos();
	public void guardar(Producto producto);
	public Producto buscarUno(Long id);
	public void eliminar(Long id);
}
