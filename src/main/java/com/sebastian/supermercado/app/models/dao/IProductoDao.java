package com.sebastian.supermercado.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sebastian.supermercado.app.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long> {
	
}
