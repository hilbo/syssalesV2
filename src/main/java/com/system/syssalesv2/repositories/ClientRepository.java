package com.system.syssalesv2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.syssalesv2.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	@Query("select c from Client c where c.email = :email")
	public Page<Client> findPerEmail(String email, Pageable page);

	@Query("select c from Client c where c.cpfOrCnpj = :cpfOrCnpj")
	public Page<Client> findByCpfOrCnpj(String cpfOrCnpj, Pageable page); 
	
}
