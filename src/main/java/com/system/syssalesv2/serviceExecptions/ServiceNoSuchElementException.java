package com.system.syssalesv2.serviceExecptions;

public class ServiceNoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceNoSuchElementException(String msg) {
		super(msg);
	}
}
