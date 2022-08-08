package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
