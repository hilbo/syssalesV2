package com.system.syssalesv2.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.syssalesv2.DTO.OrderInserDTO;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {
	
	@Autowired
	OrderService orderService;
		
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id){
		return ResponseEntity.ok(orderService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody OrderInserDTO order){
		Order orderTmp = new Order();
		orderTmp = orderService.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + orderTmp.getId()).build().toUri();
		return ResponseEntity.created(uri).build();
	}
}
