package com.system.syssalesv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.PaymentRepository;


@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
		
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment save(Payment payment) {
		
		for (Payment payment2 : paymentRepository.findAll()) {
			if (payment2.getOrder().equals(payment.getOrder())) {
				throw new RuntimeException("Erro !!!!!!!!!!!!!!!!!!!!!!");
			}
		}
		
		return paymentRepository.save(payment);
	}
}
