package com.system.syssalesv2.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.system.syssalesv2.entities.enums.StatePayment;
import com.system.syssalesv2.util.CalculatorOfDue;

@Entity
public class PaymentWithTicket extends Payment {
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime paymentDate;
	private LocalDateTime dueDate;
	
	public PaymentWithTicket() {
	}

	public PaymentWithTicket(Long id, StatePayment paymentState, LocalDateTime paymentDate, Order order) {
		super(id, paymentState, order);
		this.paymentDate = paymentDate;
		this.dueDate = CalculatorOfDue.calculateDueDate(paymentDate);
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
}
