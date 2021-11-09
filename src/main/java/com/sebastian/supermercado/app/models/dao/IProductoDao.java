package com.sebastian.supermercado.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sebastian.supermercado.app.models.entity.Producto;

public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {
	
}
