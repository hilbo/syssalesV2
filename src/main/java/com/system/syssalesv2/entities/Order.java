package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime date;
	
	@ManyToOne
	private Address deliveryaddress;
	
	@ManyToOne
	private Client client;
	
	@OneToOne(mappedBy = "order")
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, LocalDateTime date, Client client, Address deliveryaddress) {
		this.id = id;
		this.date = date;
		this.client = client;
		this.deliveryaddress = deliveryaddress;
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
	
	public Address getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(Address deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public Payment getPayment() {
		return payment;
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
