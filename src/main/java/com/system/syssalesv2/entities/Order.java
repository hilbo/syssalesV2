package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.system.syssalesv2.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;

	@ManyToOne
	private Address deliveryAddress;

	@ManyToOne
	private Client client;

	@OneToMany
	private List<Payment> payments = new ArrayList<>();

	@OneToMany
	private List<OrderItem> orderItens = new ArrayList<>();
	
	private Integer orderStatus;

	public Order() {
	}

	public Order(Long id, LocalDateTime date, Client client, Address deliveryAddress, Payment payment) {
		this.id = id;
		this.date = date;
		this.client = client;
		this.deliveryAddress = deliveryAddress;
		this.orderStatus = OrderStatus.OPEN.getCod();
		getPayments().add(payment);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
		
	public OrderStatus getOrderStatus() {
		return OrderStatus.orderStatusToEnum(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus.getCod();
	}

	public Double getAmount() {
		Double amount = 0.0;
		for (OrderItem orderItem : orderItens) {
			amount = amount + orderItem.getAmount();
		}
		return amount;
	}

	public List<OrderItem> getOrderItens() {
		return orderItens;
	}
	
		
	public List<Payment> getPayments() {
		return payments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
}
