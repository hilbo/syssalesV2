package com.system.syssalesv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.repositories.OrderRepository;


@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
		
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public Order save(Order order) {
		//Option for set of the timezone in database
		//TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return orderRepository.save(order);
	}
}
