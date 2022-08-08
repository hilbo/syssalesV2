package com.system.syssalesv2.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.services.ClientService;

@RestController
@RequestMapping("/clienties")
public class ClientResource {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(clientService.findById(id));
	}

}
