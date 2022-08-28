package com.system.syssalesv2.entities;

import javax.persistence.Entity;

import com.system.syssalesv2.entities.enums.StatePayment;

@Entity
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;
	
	public Integer numberStallments;

	public PaymentWithCard() {
	}

	public PaymentWithCard(Long id,StatePayment paymentState, Integer numberStallments) {
		super(id, paymentState);
		this.numberStallments = numberStallments;
	}

	public Integer getNumberIstallments() {
		return numberStallments;
	}

	public void setNumberIstallments(Integer numberIstallments) {
		this.numberStallments = numberIstallments;
	}
}
