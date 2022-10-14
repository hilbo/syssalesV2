package com.system.syssalesv2.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Category category){
		category = categoryService.save(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + category.getId()).build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Category category){
		categoryService.update(id, category);
		
		
		return ResponseEntity.noContent().build();
 	}
	
	
}
