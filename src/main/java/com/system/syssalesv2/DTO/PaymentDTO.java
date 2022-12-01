package com.system.syssalesv2.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.ValidationException;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.enums.PaymentState;
import com.system.syssalesv2.entities.enums.PaymentType;

public class PaymentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Long id;
	private Integer numberStallments;
	private LocalDateTime paymentDate;
	private LocalDateTime dueDate;
	private Double paymentedValue;
	private Long paymentState;
	private Long paymentType;
	
	private Order order;
	
	public PaymentDTO() {
	}

	public PaymentDTO(Long id, Integer numberStallments, LocalDateTime paymentDate, LocalDateTime dueDate,
				   Double paymentedValue, PaymentState paymentState, PaymentType paymentType, Order order) {
		this.id = id;
		this.numberStallments = numberStallments;
		this.paymentDate = paymentDate;
		this.dueDate = dueDate;
		this.paymentedValue = paymentedValue;
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
		if (paymentDate == null) {
			throw new ValidationException();
		}
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

	public Double getPaymentedValue() {
		if (paymentedValue == null) {
			throw new ValidationException();
		}
		return paymentedValue;
	}

	public void setValuePaymentedValue(Double value) {
		this.paymentedValue = value;
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

	public PaymentType getPaymentType() {
		if (paymentType == null) {
			throw new ValidationException();
		}
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
		PaymentDTO other = (PaymentDTO) obj;
		return Objects.equals(id, other.id);
	}
}
