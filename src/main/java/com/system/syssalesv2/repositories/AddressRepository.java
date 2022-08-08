package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
