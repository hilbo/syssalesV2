package com.system.syssalesv2.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.services.CategoryService;
import com.system.syssalesv2.services.ProductService;

@Configuration
public class Cid implements CommandLineRunner {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@Override
	public void run(String... args) throws Exception {
		Product product01 = new Product(100L, "Produto01", 200.0);
		productService.save(product01);
		
		Category category01 = new Category(200L, "Category01");
		categoryService.save(category01);
		
	}
}
