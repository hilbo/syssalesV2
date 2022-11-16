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
	@Autowired
	private ProductService productService;

	public OrderItem findById(Long id) {
		try {
			return orderItemRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Item de pedido não encontrado !", "OrderItem");
		}
	}
	
	@Transactional
	public OrderItem save(OrderItem orderItem) {
		orderItem.setId(null);
		return orderItemRepository.save(orderItem);
	}
	
	public OrderItem insert(OrderItem orderItem) {
		orderItem.setId(null);
		orderItem.setQuantity(orderItem.getQuantity());	
		orderItem.setDiscount(orderItem.getDiscount());				
		orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
		orderItem.setOrder(orderItem.getOrder());
		orderItem.setPrice();
		save(orderItem);
		return orderItem;
	}

	public void delete(Long id) {
		orderItemRepository.delete(findById(id));
	}

}
