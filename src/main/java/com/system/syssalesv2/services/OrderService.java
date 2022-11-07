package com.system.syssalesv2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.enums.OrderStatus;
import com.system.syssalesv2.repositories.OrderRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ClientService clientService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private ProductService productService;

	public List<Order> findAll() {
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
		// Option for set of the timezone in database
		// TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return orderRepository.save(order);
	}

	@Transactional
	public void insert(Order order) {
		try {
			Order orderTmp = new Order();
			save(orderTmp);
			orderTmp.setDate(LocalDateTime.now());
			orderTmp.setClient(clientService.findById(order.getClient().getId()));

			for (Address address : orderTmp.getClient().getAddresses()) {
				if (address.getId() == (order.getDeliveryAddress().getId())) {
					orderTmp.setDeliveryAddress(address);
				}
				if (address.getId() != (order.getDeliveryAddress().getId())) {
					throw new NoSuchElementException("Endereço de entrega não informado ou não encontrado !");
				}
			}

			orderTmp.setOrderStatus(OrderStatus.OPEN);
			
			for (OrderItem orderItem : order.getOrderItens()) {
				//OrderItem oiTmp = new OrderItem(null, orderTmp, orderItem.getDiscount(), orderItem.getQuantity(), productService.findById(orderItem.getProduct().getId()));
				orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
				orderItem.setOrder(orderTmp);
				orderItem.setPrice();
				orderTmp.getOrderItens().add(orderItemService.save(orderItem));
			}
			
			save(orderTmp);
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException(e.getMessage());
		}
	}
}
