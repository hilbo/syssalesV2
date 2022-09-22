package com.system.syssalesv2.resourcesExecpitions;

import java.io.Serializable;

public class SpecificException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer codInternal;
	private Integer status;
	private String error;
	private String defaultMessage;
	private String field;
		
	public SpecificException() {
	}
			
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public Integer getCodInternal() {
		return codInternal;
	}

	public void setCodInternal(Integer codInternal) {
		this.codInternal = codInternal;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
}
