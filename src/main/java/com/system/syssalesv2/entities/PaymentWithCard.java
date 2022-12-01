package com.system.syssalesv2.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.system.syssalesv2.entities.enums.PaymentState;
import com.system.syssalesv2.entities.enums.PaymentType;

@Entity
public class PaymentWithCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberStallments;
	@Transient
	private PaymentType paymentType = PaymentType.CARTAO;
				
	public PaymentWithCard() {
	}

	public PaymentWithCard(Long id, LocalDateTime paymentDate, PaymentState paymentState, Double paymentValue, Order order, Integer numberStallments) {
		super(id, paymentDate, paymentState, paymentValue, order);
		this.numberStallments = numberStallments; 
	}

	public Integer getNumberStallments() {
		return numberStallments;
	}

	public void setNumberStallments(Integer numberStallments) {
		this.numberStallments = numberStallments;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
