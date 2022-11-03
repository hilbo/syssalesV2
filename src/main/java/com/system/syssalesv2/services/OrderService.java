package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.repositories.OrderRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;


@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
		
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		try {
			return orderRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Pedido não encontrado !");
		}
	}
	
	public Order save(Order order) {
		//Option for set of the timezone in database
		//TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return orderRepository.save(order);
	}
}
