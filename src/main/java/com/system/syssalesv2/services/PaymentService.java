package com.system.syssalesv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.OrderRepository;
import com.system.syssalesv2.repositories.PaymentRepository;
import com.system.syssalesv2.serviceExecptions.ServiceOrderAssociateException;


@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	OrderRepository orderRepository;
		
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment save(Payment payment) {
		 try {
			 findPerOrder(payment.getOrder().getId());
			 return paymentRepository.save(payment);
		} catch (ServiceOrderAssociateException e) {
			throw new ServiceOrderAssociateException(e.getMessage());
		}
	}

	private void findPerOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).get();
		if (paymentRepository.findPerOrder(order) != null) {
			throw new ServiceOrderAssociateException("Pedido já está associado a um pagamento !");
		}
	}
}
