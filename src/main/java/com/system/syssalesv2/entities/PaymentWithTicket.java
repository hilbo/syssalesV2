package com.system.syssalesv2.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.system.syssalesv2.entities.enums.PaymentState;
import com.system.syssalesv2.entities.enums.PaymentType;

@Entity
public class PaymentWithTicket extends Payment{
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime dueDate;
	@Transient
	private PaymentType paymentType = PaymentType.BOLETO;
				
	public PaymentWithTicket() {
	}

	public PaymentWithTicket(Long id, LocalDateTime paymentDate, PaymentState paymentState, Double paymentValue, Order order, LocalDateTime dueDate) {
		super(id, paymentDate, paymentState, paymentValue, order);
		this.dueDate = dueDate; 
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

}
