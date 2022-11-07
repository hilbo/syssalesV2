package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_orderItem")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double discount;
	private Integer quantity;
	private Double price;

	@OneToOne
	private Product product;

	//@Transient
	@JsonIgnore
	@ManyToOne
	private Order order;

	public OrderItem() {
	}

	public OrderItem(Long id, Order order, Double discount, Integer quantity, Product product) {
		this.id = id;
		this.order = order;
		if (discount == null) {
			this.discount = 0.0;
		}else {
			this.discount = discount;
		}
		this.quantity = quantity;
		this.product = product;
		setPrice();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		if (discount == null) {
			this.discount = 0.0;
		}else {
			this.discount = discount;
		}
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice() {
		this.price = product.getPrice();
	}
	
	public Double getAmount() {
		Double amount = this.price * quantity;
		Double discountValue = amount * (discount / 100);
		if (discount != 0) {
			return amount - discountValue;
		}else {
			return amount;
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
