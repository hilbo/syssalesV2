package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.repositories.CategoryRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category findById(Long id) {
		try {
			return categoryRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Categoria não encontrada !");
		}
	}
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

}
