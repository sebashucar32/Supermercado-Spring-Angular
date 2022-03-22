package com.sebastian.supermercado.app.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.supermercado.app.dto.ProductoDto;
import com.sebastian.supermercado.app.models.dao.IProductoDao;
import com.sebastian.supermercado.app.models.entity.Producto;
import com.sebastian.supermercado.app.models.services.IProductoService;
import com.sebastian.supermercado.app.response.ProductoResponse;
import com.sebastian.supermercado.app.utils.ConverEntitytoDto;

@Service
public class ProductoServiceImpl implements IProductoService {
	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private ConverEntitytoDto converEntitytoDto;

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ProductoResponse> buscarProductos() {
		ProductoResponse productosResponse = new ProductoResponse();
		List<ProductoDto> productos = new ArrayList<>();
		
		try {
			List<Producto> productosEntity = productoDao.findAll();
			productos = productosEntity.stream().map(producto -> converEntitytoDto.converToDtoProducto(producto)).collect(Collectors.toList());
			productosResponse.setProductos(productos);
		} catch (Exception e) {
			return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ProductoResponse> mostrarProducto(Long id) {
		ProductoResponse productosResponse = new ProductoResponse();
		
		try {
			List<ProductoDto> listaProductos = new ArrayList<>();
			Producto productoEntity = productoDao.findById(id).orElse(null);
			ProductoDto producto = converEntitytoDto.converToDtoProducto(productoEntity);
			listaProductos.add(producto);
			productosResponse.setProductos(listaProductos);
		} catch (Exception e) {
			log.error("Error en consultar el producto del supermercado");
			return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ProductoResponse> crearProducto(ProductoDto producto) {
		ProductoResponse productosResponse = new ProductoResponse();
		
		try {
			List<ProductoDto> listaProductos = new ArrayList<>();
			Producto productoEntity = converEntitytoDto.convertToEntityProducto(producto);
				
			if(productoEntity != null) {
				productoDao.save(productoEntity);
				listaProductos.add(producto);
				productosResponse.setProductos(listaProductos);
			} else {
				log.error("No se puede guardar el producto");
				return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			log.error("Error al guardar el producto");
			return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProductoResponse> guardarProducto(ProductoDto producto, Long id) {
		ProductoResponse productosResponse = new ProductoResponse();
		
		try {
			List<ProductoDto> listaProductos = new ArrayList<>();
			Producto productoActual = productoDao.findById(id).orElse(null);
			Producto productoEnviado = converEntitytoDto.convertToEntityProducto(producto);
			
			if(productoActual != null) {
				if(productoEnviado != null) {
					productoActual.setNombre(productoEnviado.getNombre());
					productoActual.setCategoria(productoEnviado.getCategoria());
					productoActual.setPrecio(productoEnviado.getPrecio());
					productoActual.setFechaProducto(new Date());
					
					productoDao.save(productoActual);
					listaProductos.add(producto);
					productosResponse.setProductos(listaProductos);
				} else {
					log.error("error los datos enviados no son correctos para actualizar el producto");
					return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("error no existe el producto que quiere actualizar");
				return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("error en actualizar producto");
			return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<ProductoResponse> eliminarProducto(Long id) {
		ProductoResponse productosResponse = new ProductoResponse();
		List<ProductoDto> listaProductos = new ArrayList<>();
		
		try {
			Producto productoActual = productoDao.findById(id).orElse(null);
			ProductoDto productoDto = converEntitytoDto.converToDtoProducto(productoActual);
			listaProductos.add(productoDto);
			productosResponse.setProductos(listaProductos);
			productoDao.deleteById(id);
		} catch (Exception e) {
			log.error("error al eliminar el producto");
			return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoResponse>(productosResponse, HttpStatus.OK);
	}
}
