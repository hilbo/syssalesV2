package com.system.syssalesv2.entities.enums;

public enum PaymentState {
	
	PENDENTE(500l, "Pendente"),
	QUITADO(600l, "Quitado"),
	CANCELADO(600l, "Cancelado");
	
	private Long cod;
	private String descript;
	
	private PaymentState(Long cod, String descript) {
		this.cod = cod;
		this.descript = descript;
	}

	public Long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public static PaymentState statePaymentToEnum(Long cod) {
		
		for (PaymentState paymentState : PaymentState.values()) {
			if (cod.equals(paymentState.getCod())) {
				return paymentState;
			}
		}
		
		return null;
	}

}
