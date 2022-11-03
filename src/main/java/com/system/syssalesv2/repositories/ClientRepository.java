package com.system.syssalesv2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.syssalesv2.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select c from Client c where c.email = ?1")
	public List<Client> findPerEmail(String email); 
	
}
