package com.system.syssalesv2.resourcesExecpitions;

public class ResourceNoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNoSuchElementException(String msg) {
		super(msg);
	}
}
