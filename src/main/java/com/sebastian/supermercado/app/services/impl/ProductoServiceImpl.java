package com.sebastian.supermercado.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.supermercado.app.dto.ProductoDto;
import com.sebastian.supermercado.app.models.dao.IProductoDao;
import com.sebastian.supermercado.app.models.entity.Producto;
import com.sebastian.supermercado.app.models.services.IProductoService;
import com.sebastian.supermercado.app.utils.ConverEntitytoDto;

@Service
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private ConverEntitytoDto converEntitytoDto;

	@Override
	@Transactional(readOnly=true)
	public List<ProductoDto> buscarTodos() {
		List<ProductoDto> productos = null;
		List<Producto> productosEntity = productoDao.findAll();
		productos = productosEntity.stream().map(producto -> converEntitytoDto.converToDtoProducto(producto)).collect(Collectors.toList());
		
		return productos;
	}
	
	@Override
	@Transactional(readOnly=true)
	public ProductoDto buscarUno(Long id) {
		ProductoDto producto = null;
		Producto productoEntity = productoDao.findById(id).orElse(null);
		producto = converEntitytoDto.converToDtoProducto(productoEntity);
		return producto;
	}

	@Override
	@Transactional
	public void guardar(ProductoDto producto) {
		Producto productoEntity = null;
		productoEntity = converEntitytoDto.convertToEntityProducto(producto);
		productoDao.save(productoEntity);
	}
	
	@Override
	@Transactional
	public void eliminar(Long id) {
		productoDao.deleteById(id);
	}
}
