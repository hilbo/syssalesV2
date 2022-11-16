package com.system.syssalesv2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderInserDTO;
import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.OrderRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.Validation;

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
	@Autowired
	private PaymentService paymentService;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		try {
			return orderRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Pedido não encontrado !", "order");
		}
	}

	public Order save(Order order) {
		// Option for set of the timezone in database
		// TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		return orderRepository.save(order);
	}

	@Transactional
	public Order insert(OrderInserDTO orderInsertDto) {
		try {
			Order order = new Order();
			save(order);

			order.setClient(clientService.findById(Long.parseLong(orderInsertDto.getClientId())));
			order.setDate(LocalDateTime.now());

			for (Address address : order.getClient().getAddresses()) {
				if (address.getId().equals(Long.parseLong(orderInsertDto.getDeliveryAddressId()))) {
					order.setDeliveryAddress(address);
				}
				if (!address.getId().equals((Long.parseLong(orderInsertDto.getDeliveryAddressId())))) {
					throw new NoSuchElementException("Endereço de entrega não informado ou não encontrado !");
				}
			}
			
			for (OrderItem orderItem : orderInsertDto.getOrderItens()) {
				orderItem.setQuantity(orderItem.getQuantity());	
				orderItem.setDiscount(orderItem.getDiscount());				
				orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
				orderItem.setOrder(order);
				orderItem.setPrice();
				order.getOrderItens().add(orderItemService.insert(orderItem));
			}

			Double paymentAmount = 0.0;
			for (Payment payment : orderInsertDto.getPayments()) {
				payment.setPaymentState(payment.getPaymentState());
				payment.setOrder(order);
				order.getPayments().add(paymentService.save(payment));
				paymentAmount = paymentAmount + payment.getPaymentValue();
			}
			update(order);
			return order;
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException(e.getMessage(), "");
		}

	}

	public void update(Order order) {
		orderRepository.save(order);
	}

	@Transactional
	public Order insert2(Order order, String str) {

		Validator validator = new Validation();

		try {
			validator.validOrderInsert(order);

			Order orderTmp = new Order();
			save(orderTmp);

			if (str.equals("1")) {
				// orderTmp.setPayment(paymentService.save(new PaymentWithCard(null,
				// PaymentState.PENDENTE, 2, orderTmp)));
			}

			orderTmp.setClient(clientService.findById(order.getClient().getId()));
			orderTmp.setDate(LocalDateTime.now());

			for (Address address : clientService.findById(order.getClient().getId()).getAddresses()) {
				if (address.getId() == (order.getDeliveryAddress().getId())) {
					orderTmp.setDeliveryAddress(address);
				}
				if (address.getId() != (order.getDeliveryAddress().getId())) {
					throw new NoSuchElementException("Endereço de entrega não informado ou não encontrado !");
				}
			}

			for (OrderItem orderItem : order.getOrderItens()) {
				orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
				orderItem.setOrder(orderTmp);
				orderItem.setPrice();
				orderTmp.getOrderItens().add(orderItemService.save(orderItem));
			}

			return save(orderTmp);
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException(e.getMessage(), "");
		}

	}
}
