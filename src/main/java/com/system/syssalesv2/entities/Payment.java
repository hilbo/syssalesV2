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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.syssalesv2.entities.enums.PaymentState;
import com.system.syssalesv2.entities.enums.PaymentType;

@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numberStallments;
	private LocalDateTime paymentDate;
	private LocalDateTime dueDate;
	private Double paymentValue;
	private Long paymentState;
	private Long paymentType;
	
	@JsonIgnore
	@OneToOne
	private Order order;
	
	public Payment() {
	}

	public Payment(Long id, Integer numberStallments, LocalDateTime paymentDate, LocalDateTime dueDate,
				   Double paymentValue, PaymentState paymentState, PaymentType paymentType, Order order) {
		this.id = id;
		this.numberStallments = numberStallments;
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
		this.paymentValue = paymentValue;
		this.paymentState = paymentState.getCod();
		this.paymentType = paymentType.getCod();
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Double getPaymentValue() {
		return paymentValue;
	}

	public void setPaymentValue(Double paymentValue) {
		this.paymentValue = paymentValue;
	}

	public PaymentState getPaymentState() {
		return PaymentState.statePaymentToEnum(this.paymentState);
	}

	public void setPaymentState(PaymentState paymentState) {
		this.paymentState = paymentState.getCod();
	}

	public PaymentType getPaymentType() {
		return PaymentType.typeClientToEnum(this.paymentType);
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType.getCod();
	}
	
	public Integer getNumberStallments() {
		return numberStallments;
	}

	public void setNumberStallments(Integer numberStallments) {
		this.numberStallments = numberStallments;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

}
