package com.system.syssalesv2.configurations;

import java.util.Arrays;
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
		
		//Instanceament de Producties, Categories and associations.
		Product product01 = new Product(null, "Produto01", 200.0);
		productService.save(product01);
		Product product02 = new Product(null, "Produto02", 300.0);
		productService.save(product02);
		Product product03 = new Product(null, "Produto02", 400.0);
		productService.save(product03);
		
		Category category01 = new Category(null, "Category01");
		categoryService.save(category01);
		Category category02 = new Category(null, "Category02");
		categoryService.save(category02);
		
		category01.getProducties().addAll(Arrays.asList(product01, product02, product03));
		categoryService.save(category01);
		category02.getProducties().add(product02);
		categoryService.save(category02);
				
	}
}
