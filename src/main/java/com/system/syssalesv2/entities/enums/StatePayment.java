package com.system.syssalesv2.entities.enums;

public enum StatePayment {
	
	PENDENTE(500, "Pendente"),
	QUITADO(600, "Quitado"),
	CANCELADO(600, "Cancelado");
	
	private Integer cod;
	private String descript;
	
	private StatePayment(Integer cod, String descript) {
		this.cod = cod;
		this.descript = descript;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	public static StatePayment statePaymentToEnum(Integer cod) {
		
		for (StatePayment statePayment : StatePayment.values()) {
			if (cod.equals(statePayment.getCod())) {
				return statePayment;
			}
		}
		
		return null;
	}

}
