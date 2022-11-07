package com.system.syssalesv2.services;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.repositories.OrderItemRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem findById(Long id) {
		try {
			return orderItemRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Item de pedido não encontrado !");
		}
	}
	
	@Transactional
	public OrderItem save(OrderItem orderItem) {
		orderItem.setId(null);
		return orderItemRepository.save(orderItem);
	}

	public void delete(Long id) {
		orderItemRepository.delete(findById(id));
	}

}
