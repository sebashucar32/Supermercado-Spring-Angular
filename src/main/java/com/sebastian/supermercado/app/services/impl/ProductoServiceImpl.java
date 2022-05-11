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
import com.sebastian.supermercado.app.response.Response;
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
	public ResponseEntity<?> buscarProductos() {
		Response productosResponse = new Response();
		List<ProductoDto> productos = new ArrayList<>();
		
		try {
			List<Producto> productosEntity = productoDao.findAll();
			productos = productosEntity.stream().map(producto -> converEntitytoDto.converToDtoProducto(producto)).collect(Collectors.toList());
			productosResponse.setProductos(productos);
		} catch (Exception e) {
			return new ResponseEntity<Response>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Response>(productosResponse, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<?> mostrarProducto(Long id) {
		Response productosResponse = new Response();
		ProductoDto producto = null;
		
		try {
			Producto productoEntity = productoDao.findById(id).orElse(null);
			
			if(productoEntity == null) {
				log.error("El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
				return new ResponseEntity<Response>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				producto = converEntitytoDto.converToDtoProducto(productoEntity);
			}
		} catch (Exception e) {
			log.error("Error en consultar el producto del supermercado");
			return new ResponseEntity<Response>(productosResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoDto>(producto, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<?> crearProducto(ProductoDto producto) {
		try {
			Producto productoEntity = converEntitytoDto.convertToEntityProducto(producto);
				
			if(productoEntity != null) {
				productoDao.save(productoEntity);
			} else {
				log.error("No se puede guardar un producto nulo");
				return new ResponseEntity<ProductoDto>(producto, HttpStatus.BAD_REQUEST);  // error 404 : indica que el servidor no puede o no procesará la petición debido a algo que es percibido como un error del cliente
			}
		} catch (Exception e) {
			log.error("Error inesperado en el servidor");
			return new ResponseEntity<ProductoDto>(producto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		return new ResponseEntity<ProductoDto>(producto, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> guardarProducto(ProductoDto producto, Long id) {
		try {
			Producto productoActual = productoDao.findById(id).orElse(null);
			Producto productoEnviado = converEntitytoDto.convertToEntityProducto(producto);
			
			if(productoActual != null) {
				if(productoEnviado != null) {
					productoActual.setNombre(productoEnviado.getNombre());
					productoActual.setCategoria(productoEnviado.getCategoria());
					productoActual.setPrecio(productoEnviado.getPrecio());
					productoActual.setFechaProducto(new Date());
					
					productoDao.save(productoActual);
				} else {
					log.error("error los datos enviados no son correctos para actualizar el producto");
					return new ResponseEntity<ProductoDto>(producto, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("error no existe el producto que quiere actualizar");
				return new ResponseEntity<ProductoDto>(producto, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error inesperado en el servidor no se puede actualizar producto");
			return new ResponseEntity<ProductoDto>(producto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoDto>(producto, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<?> eliminarProducto(Long id) {
		ProductoDto productoDto = new ProductoDto();
		
		try {
			Producto productoActual = productoDao.findById(id).orElse(null);
			
			if(productoActual == null) {
				log.error("error no existe el producto que quiere eliminar");
				return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.NOT_FOUND);
			} else {
				productoDto = converEntitytoDto.converToDtoProducto(productoActual);
				productoDao.deleteById(id);
			}
		} catch (Exception e) {
			log.error("error al eliminar el producto");
			return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoDto>(productoDto, HttpStatus.OK);
	}
}
