package com.system.syssalesv2.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderDTO;
import com.system.syssalesv2.DTO.PaymentDTO;
import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.entities.enums.OrderStatus;
import com.system.syssalesv2.entities.enums.PaymentState;
import com.system.syssalesv2.repositories.OrderRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.ValidationOrder;

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

	@Transactional
	public Order insert(OrderDTO orderDto) {
		// Option for set of the timezone in database
		// TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
		Validator validator = new ValidationOrder();

		try {
			validator.validOrder(orderDto, productService, paymentService);
			Order order = new Order();
			orderRepository.save(order);

			order.setClient(clientService.findById(Long.parseLong(orderDto.getClientId())));
			order.setDate(LocalDateTime.now());

			for (Address address : order.getClient().getAddresses()) {
				if (address.getId().equals(Long.parseLong(orderDto.getDeliveryAddressId()))) {
					order.setDeliveryAddress(address);
				}
				if (!address.getId().equals((Long.parseLong(orderDto.getDeliveryAddressId())))) {
					throw new NoSuchElementException("Endereço de entrega não informado ou não encontrado !");
				}
			}

			for (OrderItem orderItem : orderDto.getOrderItens()) {
				orderItem.setQuantity(orderItem.getQuantity());
				orderItem.setDiscount(orderItem.getDiscount());
				orderItem.setProduct(productService.findById(orderItem.getProduct().getId()));
				orderItem.setOrder(order);
				orderItem.setPrice();
				order.getOrderItens().add(orderItemService.insert(orderItem));
			}

			for (PaymentDTO paymentDTO : orderDto.getPaymentsDTO()) {
				if (paymentService.mountPayment(paymentDTO, order, validator.getError()) == null) {
					throw new ValidationException();
				}
				if (paymentService.mountPayment(paymentDTO, order, validator.getError()) != null) {
					order.getPayments().add(paymentService
							.insert(paymentService.mountPayment(paymentDTO, order, validator.getError())));
				}
			}

			validator.validPaymentOrder(order);

			closeOrder(order);

			orderRepository.save(order);
			return order;
		} catch (ValidationException e) {
			throw new ValidationExceptionService("", validator.getError());
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException(e.getMessage(), "");
		}
	}

	public void closeOrder(Order order) {
		Validator validator = new ValidationOrder();
		try {
			validator.validPaymentOrder(order);
			for (Payment payment : order.getPayments()) {
				if (!payment.getPaymentState().equals(PaymentState.QUITADO)) {
					order.setOrderStatus(OrderStatus.OPEN);
				} else {
					order.setOrderStatus(OrderStatus.CLOSE);
				}
			}
		} catch (ValidationException e) {
			throw new ValidationExceptionService("Erro de validação !", validator.getError());
		}
	}

	public void update(Order order) {
		orderRepository.save(order);
	}

}
