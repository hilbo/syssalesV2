package com.system.syssalesv2.validatories.execptions;

import com.system.syssalesv2.resourcesExecpitions.StandardException;

public class ValidationExceptionService extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private StandardException error;
	
	public ValidationExceptionService(String message, StandardException error) {
		super(message);
		this.error = error;
	}
	
	public StandardException getError() {
		return error;
	}
	
}
