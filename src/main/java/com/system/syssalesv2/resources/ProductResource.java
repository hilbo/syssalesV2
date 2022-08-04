package com.system.syssalesv2.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.services.ProductService;

@RestController
@RequestMapping("/producties")
public class ProductResource {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		return ResponseEntity.ok().body(productService.findAll());
	}

}
