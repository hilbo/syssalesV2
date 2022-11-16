package com.system.syssalesv2.entities.enums;

public enum OrderStatus {
	
	OPEN(100, "Aberto"),
	CLOSE(200, "Fechado");
	
	private Integer cod;
	private String descript;
	
	private OrderStatus(Integer cod, String descript) {
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
	
	public static OrderStatus orderStatusToEnum(Integer cod) {
		if (cod == null) {
			cod = 0;
		}
		for (OrderStatus orderStatus : OrderStatus.values()) {
			if (cod.equals(orderStatus.getCod())) {
				return orderStatus;
			}
		}
		return null;
	}

}
