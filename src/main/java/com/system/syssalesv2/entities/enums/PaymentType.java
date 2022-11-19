package com.system.syssalesv2.entities.enums;

public enum PaymentType {

	CARTAO(900l, "Cartão"), 
	BOLETO(800l, "Pessoa Jurídica"),
	DINHEIRO(700l, "Dinheiro"),
	NAOIDENTIFICADO(300l, "Não Identificada");

	private Long cod;
	private String descript;

	private PaymentType(Long cod, String descript) {
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

	public static PaymentType typeClientToEnum(Long cod) {
		for (PaymentType clientType : PaymentType.values()) {
			if (cod.equals(clientType.getCod())) {
				return clientType;
			}
		}
		return NAOIDENTIFICADO;
	}

}
