package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	@Query("select p from Payment p WHERE p.order = ?1")
	Payment findPerOrder (Order order);
}
