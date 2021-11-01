package com.sebastian.supermercado.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sebastian.supermercado.app.models.dao.IProductoDao;
import com.sebastian.supermercado.app.models.entity.Producto;

@Controller
@SessionAttributes("producto")
public class ProductoController {
	@Autowired
	private IProductoDao productoDao;
	
	@RequestMapping(value="/listarProductos", method=RequestMethod.GET)
	public String listarProductos(Model model) {
		model.addAttribute("titulo", "Listado de Productos");
		model.addAttribute("productos", productoDao.buscarTodos());
		return "mostrarProductos";
	}
	
	@GetMapping(value="/crearProducto")
	public String crearProducto(Map<String, Object> model) {
		Producto producto = new Producto();
		model.put("titulo", "Crea tu Producto");
		model.put("producto", producto);
		return "crearProducto";
	}
	
	@GetMapping(value="/crearProducto/{id}")
	public String editarProducto(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Producto producto = null;
		
		if(id > 0) {
			producto = productoDao.buscarUno(id);
		} else {
			return "redirect:/mostrarProductos";
		}
		
		model.put("titulo", "Editar Producto");
		model.put("producto", producto);
		return "crearProducto";
	}
	
	@PostMapping(value="/crearProducto")
	public String guardarProducto(@Valid Producto producto, BindingResult resultado, Model model, SessionStatus status) {
		if(resultado.hasErrors()) {
			model.addAttribute("titulo", "Crea tu Producto");
			return "crearProducto";
		}
		
		productoDao.guardar(producto);
		status.setComplete();
		return "redirect:/listarProductos";
	}
	
	@GetMapping(value="/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable(value="id") Long id) {
		if(id > 0) {
			productoDao.eliminar(id);
		}
		
		return "redirect:/listarProductos";
	}
}
