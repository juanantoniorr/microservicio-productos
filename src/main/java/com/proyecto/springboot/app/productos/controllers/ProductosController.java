package com.proyecto.springboot.app.productos.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.productos.models.entity.Producto;
import com.proyecto.springboot.app.productos.models.service.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

	@Autowired
	IProductoService productoService;
	
	@GetMapping ("/listar")
	public List <Producto> listar(){
		
		return productoService.findAll();
		
	}
	
	@GetMapping ("/listar/{id}")
	public Producto detalle (@PathVariable Long id) {
		
		return productoService.findById(id);
	}
}
