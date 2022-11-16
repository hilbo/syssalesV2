package com.system.syssalesv2.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;

public class OrderInserDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private String clientId;
	private String deliveryAddressId;
	private List<OrderItem> orderItens = new ArrayList<>();
	private List<Payment> payments = new ArrayList<>();
		
	public OrderInserDTO() {
	}
	
	public OrderInserDTO(Order order) {
		this.clientId = order.getClient().getId().toString();
		//this.deliveryAddressId = order.getClient().getAddresses().;
		//this.PaymentType = paymentType;
		//this.statePaymentId = statePaymentId;
		//this.numberStallments = numberStallments;
	}

	public OrderInserDTO(String clientId, String deliveryAddressId, Payment payment) {
		this.clientId = clientId;
		this.deliveryAddressId = deliveryAddressId;
		getPayments().add(payment);
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(String deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public List<OrderItem> getOrderItens() {
		return orderItens;
	}
	
	
		
}
