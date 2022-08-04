package com.system.syssalesv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public Product save(Product product) {
		return productRepository.save(product);
	}

}
