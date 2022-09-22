package com.system.syssalesv2.validatories.execptions;

public class ValidationExceptionService extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ValidationExceptionService(String message) {
		super(message);
	}
}
