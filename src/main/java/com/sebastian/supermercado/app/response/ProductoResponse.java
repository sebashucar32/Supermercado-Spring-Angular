package com.sebastian.supermercado.app.response;

import java.util.List;

import com.sebastian.supermercado.app.dto.ProductoDto;

import lombok.Data;

@Data
public class ProductoResponse {
	private List<ProductoDto> productos;
}