package com.system.syssalesv2.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.enums.OrderStatus;


public class OrderDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String clientId;
	private LocalDateTime date;
	private String deliveryAddressId;
	private List<OrderItem> orderItens = new ArrayList<>();
	private List<PaymentDTO> paymentsDTO = new ArrayList<>();
	private OrderStatus orderStatus;
		
	public OrderDTO() {
	}
	
	public OrderDTO(Order order, Object paymentType, Object statePaymentId, Object numberStallments, PaymentDTO paymentDTO) {
		this.clientId = order.getClient().getId().toString();
		this.deliveryAddressId = order.getClient().getAddresses().toString();
		this.date = LocalDateTime.now();
		getPaymentsDTO().add(paymentDTO);
	}
	
	public String getClientId() {
		if (clientId == null) {
			throw new ValidationException();
		}
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDeliveryAddressId() {
		if (deliveryAddressId == null) {
			throw new ValidationException();
		}
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(String deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public List<OrderItem> getOrderItens() {
		return orderItens;
	}

	public List<PaymentDTO> getPaymentsDTO() {
		return paymentsDTO;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
