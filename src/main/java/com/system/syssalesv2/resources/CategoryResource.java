package com.system.syssalesv2.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService categoryService;
			
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(categoryService.findById(id));
	}
}
