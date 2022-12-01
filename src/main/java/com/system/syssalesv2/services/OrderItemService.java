package com.system.syssalesv2.services;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.repositories.OrderItemRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;

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
	public OrderItem insert(OrderItem orderItem) {
			
		orderItem.setId(null);
		//Validator validator = new ValidationOrderItem();
		try {
			orderItem.setId(null);
			
			//validator.validOrderItem(orderItem, productService);
						
			orderItem.setQuantity(orderItem.getQuantity());	
			orderItem.setDiscount(orderItem.getDiscount());				
			orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
			orderItem.setOrder(orderItem.getOrder());
			orderItem.setPrice();
			return orderItemRepository.save(orderItem);
		} catch (ValidationExceptionService e) {
			throw new ValidationExceptionService(e.getMessage(), e.getError());
		} catch (ServiceNoSuchElementException e) {
			throw new ServiceNoSuchElementException("erro", "prod null");
		}
	}

	public void delete(Long id) {
		orderItemRepository.delete(findById(id));
	}

}
