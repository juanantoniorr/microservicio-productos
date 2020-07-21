package com.proyecto.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.springboot.app.productos.models.dao.ProductoDao;
import com.proyecto.springboot.app.productos.models.entity.Producto;

@Service
@Transactional(readOnly = true)
public class ProductoServiceImpl implements IProductoService {
	@Autowired
	ProductoDao productoDao;

	@Override
	public List<Producto> findAll() {
		
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	public Producto findById(Long id) {
		
		return productoDao.findById(id).orElse(null);
	}

}
