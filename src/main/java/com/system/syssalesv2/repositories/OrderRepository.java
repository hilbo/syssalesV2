package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
