package com.sebastian.supermercado.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class ProductoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nombre;
	private String categoria;
	private BigDecimal precio;
	private Date fechaProducto;
}
