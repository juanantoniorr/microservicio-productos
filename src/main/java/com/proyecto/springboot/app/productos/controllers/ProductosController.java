package com.proyecto.springboot.app.productos.controllers;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.springboot.app.productos.models.entity.Producto;
import com.proyecto.springboot.app.productos.models.service.IProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
	@Value("${server.port}") //Tenemos las 2 formas de tomar variables del properties
	private Integer puerto;
	@Autowired
	private Environment env;

	@Autowired
	IProductoService productoService;
	
	@GetMapping ("/listar")
	public List <Producto> listar(){
		
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(puerto);
			return producto;
		}).collect(Collectors.toList());
		
	}
	
	@GetMapping ("/listar/{id}")
	public Producto detalle (@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		return producto;
	}
	@PostMapping ("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto guardar(@RequestBody Producto producto) { 
		//con la anotacion request body indicamos que el producto vendra dentro del body del json
		return productoService.save(producto);
		
	}
	@DeleteMapping ("/eliminar/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void eliminar (@PathVariable Long id) {
		productoService.deleteById(id);
	}
	@PutMapping ("/editar/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public Producto actualizar(@RequestBody Producto producto,@PathVariable Long id) {
		Producto productoDB = productoService.findById(id);
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		productoDB.setCreateAt(new Date());
		return productoService.save(productoDB);
		
		
	}
}
