package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.PaymentRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
		
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment findById(Long id) {
		try {
			return paymentRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Pagamento não encontrado !", "Payment");
		}
	}
	
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public Payment insert(Payment payment){
		if (payment.equals(null)) {
			throw new RuntimeException("Erro aqui");
		}
		
		if (payment.getPaymentState().equals(null)) {
			throw new RuntimeException("Erro aqui");
		}
		
		
		return save(payment);
	}
		
	public void delete(Long id) {
		paymentRepository.delete(findById(id));
		
	}
}
