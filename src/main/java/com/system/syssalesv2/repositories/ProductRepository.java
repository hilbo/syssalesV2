package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
