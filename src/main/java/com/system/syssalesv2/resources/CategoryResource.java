package com.system.syssalesv2.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.syssalesv2.DTO.CategoryDTO;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.serviceExecptions.ServiceDataIntegrityViolationException;
import com.system.syssalesv2.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		return ResponseEntity.ok().body(categoryService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoryDTO>> findPage(@PageableDefault(size = 10, sort = "name", direction = Direction.DESC) Pageable page){
		    return ResponseEntity.ok().body(categoryService.findPage(page));
	}

	@PostMapping
	public ResponseEntity<Void> save(@RequestBody Category category) {
		category = categoryService.save(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + category.getId()).build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Category category) {
		categoryService.update(id, category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			categoryService.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceDataIntegrityViolationException("Existem itens associados a esta Categoria !");
		}
		return ResponseEntity.noContent().build();
	}

}
