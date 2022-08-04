package com.system.syssalesv2.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.system.syssalesv2.entities.Product;

@Configuration
public class Cid implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		Product product01 = new Product(100L, "Produto01", 200.0);
		
		
	}
}
