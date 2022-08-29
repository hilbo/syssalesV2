package com.system.syssalesv2.entities;

import javax.persistence.Entity;

import com.system.syssalesv2.entities.enums.StatePayment;

@Entity
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	public Integer numberStallments;

	public PaymentWithCard() {
	}

	public PaymentWithCard(Long id,StatePayment paymentState, Integer numberStallments, Order order) {
		super(id, paymentState, order);
		this.numberStallments = numberStallments;
	}

	public Integer getNumberStallments() {
		return numberStallments;
	}

	public void setNumberStallments(Integer numberIstallments) {
		this.numberStallments = numberIstallments;
	}
}
