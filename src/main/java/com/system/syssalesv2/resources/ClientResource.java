package com.system.syssalesv2.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.system.syssalesv2.DTO.ClientDTO;
import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.serviceExecptions.ServiceDataIntegrityViolationException;
import com.system.syssalesv2.services.ClientService;

@RestController
@RequestMapping("/clienties")
public class ClientResource {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(clientService.findById(id));
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClientDTO>> findPage(@PageableDefault(size = 10, sort = "name", direction = Direction.DESC) Pageable page){
		    return ResponseEntity.ok().body(clientService.findPage(page));
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<Client>> search(@RequestParam(value = "attribut") String attribut, @RequestParam(value = "value") String value, Pageable page){
		clientService.findByAttribut(attribut, value, page);
		return ResponseEntity.ok(clientService.findByAttribut(attribut, value, page));
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid ClientDTO clientDTO) {
		ClientDTO clientPageDTO = clientService.insert(clientDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + clientPageDTO.getId()).build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Client client) {
		clientService.update(id, client);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			clientService.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceDataIntegrityViolationException("Existem itens associados a esta Categoria !");
		}
		return ResponseEntity.noContent().build();
	}
}
