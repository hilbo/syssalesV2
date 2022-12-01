package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.PaymentRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.ValidationPayment;

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
	
	@Transactional
	public Payment insert(Payment payment){
		Validator validator = new ValidationPayment();
		try {
			//validator.validPayment(payment, paymentRepository);
			return save(payment);
		} catch (ValidationException e) {
			throw new ValidationExceptionService("", validator.getError());
		}
			
	}
		
	public void delete(Long id) {
		paymentRepository.delete(findById(id));
		
	}
}
