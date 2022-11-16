package com.system.syssalesv2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.CategoryDTO;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.repositories.CategoryRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryDTO> findAll() {
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		for (Category category : categoryRepository.findAll()) {
			CategoryDTO categoryDTO = new CategoryDTO(category);
			listCategoryDTO.add(categoryDTO);
		}
		return listCategoryDTO;
	}

	public Category findById(Long id) {
		try {
			return categoryRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Categoria não encontrada !", "Categoria");
		}
	}

	public Page<CategoryDTO> findPage(Pageable page) {
		Page<Category> pageCategory = categoryRepository.findAll(page);
		Page<CategoryDTO> pageCategoryDTO = pageCategory.map(x -> new CategoryDTO(x));
		return pageCategoryDTO;
	}

	@Transactional
	public Category save(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

	@Transactional
	public CategoryDTO saveDTO(CategoryDTO categoryDTO) {
		categoryDTO.setId(null);
		return new CategoryDTO(categoryRepository.save(categoryDtoFromCategory(categoryDTO)));
	}

	private Category categoryDtoFromCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setName(categoryDTO.getName());
		return category;
	}

	@Transactional
	public void update(Long id, Category category) {
		Category catTmp = findById(id);
		if (!category.getName().equals(null)) {
			catTmp.setName(category.getName());
		}
		categoryRepository.save(catTmp);
	}

	public void delete(Long id) {
		categoryRepository.delete(findById(id));
	}

}
