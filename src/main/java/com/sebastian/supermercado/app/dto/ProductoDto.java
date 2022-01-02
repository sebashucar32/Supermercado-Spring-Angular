package com.sebastian.supermercado.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String categoria;
	private BigDecimal precio;
	private Date fechaProducto;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public Date getFechaProducto() {
		return fechaProducto;
	}
	
	public void setFechaProducto(Date fechaProducto) {
		this.fechaProducto = fechaProducto;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
