package com.system.syssalesv2.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.syssalesv2.DTO.ProductDTO;
import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.services.ProductService;

@RestController
@RequestMapping("/producties")
public class ProductResource {
	@Autowired
	ProductService productService;

	@GetMapping()
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable page) {
		return ResponseEntity.ok().body(productService.findAll(page));
	}

	@GetMapping("/search")
	public ResponseEntity<Page<Product>> findByNameAndCategories(@RequestParam(value = "name") String productName
																, @RequestParam(value = "categoriesId") List<String> categoriesId
																, Pageable page){	
		Page<Product> productiesPage =productService.findByNameAndCategories(productName, categoriesId, page);
		return ResponseEntity.ok(productiesPage);
	}

}
