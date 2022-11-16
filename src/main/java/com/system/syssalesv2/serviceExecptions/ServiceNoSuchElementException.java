package com.system.syssalesv2.serviceExecptions;

public class ServiceNoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String field;
	
	public ServiceNoSuchElementException(String msg, String field) {
		super(msg);
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
