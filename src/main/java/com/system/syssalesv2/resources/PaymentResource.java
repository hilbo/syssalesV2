package com.system.syssalesv2.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.syssalesv2.DTO.ProductDTO;
import com.system.syssalesv2.services.ProductService;

@RestController
@RequestMapping("/payments")
public class PaymentResource {
	@Autowired
	ProductService productService;

	@GetMapping()
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable page) {
		return ResponseEntity.ok().body(productService.findAll(page));
	}

	

}
