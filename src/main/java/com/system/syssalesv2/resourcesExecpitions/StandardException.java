package com.system.syssalesv2.resourcesExecpitions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StandardException implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String defaultMessage;
	private String path;
		
	private List<SpecificException> errors = new ArrayList<>();
	
	public StandardException() {
	}
		
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<SpecificException> getErros() {
		return errors;
	}
}
