package com.sebastian.supermercado.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.supermercado.app.models.dao.IProductoDao;
import com.sebastian.supermercado.app.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Producto> buscarTodos() {
		return (List<Producto>) productoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Producto buscarUno(Long id) {
		return productoDao.findById(id).orElse(null); // Retorna el producto si no lo encuentra retorna null
	}

	@Override
	@Transactional
	public void guardar(Producto producto) {
		productoDao.save(producto);
	}
	
	@Override
	@Transactional
	public void eliminar(Long id) {
		productoDao.deleteById(id);
	}
}
