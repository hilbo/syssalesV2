package com.system.syssalesv2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.ProductDTO;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.repositories.ProductRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryService categoryService;

	public Page<ProductDTO> findAll(Pageable page) {
		Page<Product> producties = productRepository.findAll(page);
		Page<ProductDTO> productiesDTO = producties.map(product -> new ProductDTO(product));
		return productiesDTO;
	}
	
	public Product findById(Long id) {
		try {
			if (id == null) {
				id = 0l;
			}
			return productRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Produto não encontrado !", "Product");
		}	
	}

	public Page<Product> findByNameAndCategories(String productName, List<String> categoriesIds, Pageable page) {
		List<Category> categories = new ArrayList<>();
		for (String categoryStr : categoriesIds) {
			Category category = new Category();
			category = categoryService.findById(Long.parseLong(categoryStr));
			categories.add(category);
		}
		return productRepository.findByNameAndCategories(productName, categories, page);
	}

	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	public void saveAll(List<Product> producties) {
		productRepository.saveAll(producties);
	}

}
