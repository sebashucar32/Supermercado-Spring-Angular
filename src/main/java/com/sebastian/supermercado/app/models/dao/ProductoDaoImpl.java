package com.sebastian.supermercado.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.supermercado.app.models.entity.Producto;

@Repository
public class ProductoDaoImpl implements IProductoDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Producto> buscarTodos() {
		return em.createQuery("from Producto").getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Producto buscarUno(Long id) {
		return em.find(Producto.class, id);
	}

	@Override
	@Transactional
	public void guardar(Producto producto) {
		if(producto.getId() != null && producto.getId() > 0) {
			em.merge(producto);
		} else {
			em.persist(producto);
		}
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		em.remove(buscarUno(id));
	}
}
