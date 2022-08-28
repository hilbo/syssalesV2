package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
