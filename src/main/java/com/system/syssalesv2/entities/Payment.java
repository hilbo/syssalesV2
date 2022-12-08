package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.ValidationException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.syssalesv2.entities.enums.PaymentState;

@Entity
@Table(name = "tb_payment")
public abstract class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime paymentDate;
	private Long paymentState;
	private Double paymentValue;
		
	@JsonIgnore
	@OneToOne
	private Order order;
	
	public Payment() {
	}

	public Payment(Long id, LocalDateTime paymentDate, PaymentState paymentState, Double paymentValue, Order order) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.paymentState = paymentState.getCod();
		this.paymentValue = paymentValue;
		this.order = order;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getPaymentDate() {
		if (paymentDate == null) {
			throw new ValidationException();
		}
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentState getPaymentState() {
		if (paymentState == null) {
			throw new ValidationException();
		}
		return PaymentState.statePaymentToEnum(this.paymentState);
	}

	public void setPaymentState(PaymentState paymentState) {
		this.paymentState = paymentState.getCod();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public Double getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(Double paymentValue) {
		this.paymentValue = paymentValue;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

	
}
