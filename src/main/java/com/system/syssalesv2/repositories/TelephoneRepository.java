package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Telephone;

public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
}
