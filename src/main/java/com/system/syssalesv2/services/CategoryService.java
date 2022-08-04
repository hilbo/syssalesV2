package com.system.syssalesv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

}
