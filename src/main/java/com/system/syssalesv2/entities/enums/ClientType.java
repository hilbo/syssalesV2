package com.system.syssalesv2.entities.enums;

public enum ClientType {

	PESSOAFISICA(100, "Pessoa Física"), 
	PESSOAJURIDICA(200, "Pessoa Jurídica"),
	NAOIDENTIFICADO(300, "Pessoa Não Identificada");

	private Integer cod;
	private String descript;

	private ClientType(Integer cod, String descript) {
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

	public static ClientType typeClientToEnum(Integer cod) {
		for (ClientType clientType : ClientType.values()) {
			if (cod.equals(clientType.getCod())) {
				return clientType;
			}
		}
		return ClientType.NAOIDENTIFICADO;
	}

}
