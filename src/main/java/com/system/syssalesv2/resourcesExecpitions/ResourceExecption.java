package com.system.syssalesv2.resourcesExecpitions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.system.syssalesv2.serviceExecptions.ServiceDataIntegrityViolationException;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;

@ControllerAdvice
public class ResourceExecption {
	
	@ExceptionHandler(ServiceNoSuchElementException.class)
	public ResponseEntity<StandardException> ServiceNoSuchElementException(ServiceNoSuchElementException e, HttpServletRequest request) {
		StandardException error = new StandardException();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("NOT FOUND");
		error.setPath(request.getRequestURI());
		error.setDefaultMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ValidationExceptionService.class)
	public ResponseEntity<StandardException> ValidationExceptionService(ValidationExceptionService e, HttpServletRequest request) {
		StandardException error = new StandardException();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError(HttpStatus.BAD_REQUEST.name());
		error.setPath(request.getRequestURI());
		error.setDefaultMessage(e.getMessage());
		error.getErros().addAll(e.getError().getErros());
						
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(ServiceDataIntegrityViolationException.class)
	public ResponseEntity<StandardException> ServiceDataIntegrityViolationException (ServiceDataIntegrityViolationException e, HttpServletRequest request) {
		StandardException error = new StandardException();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("BAD REQUEST");
		error.setPath(request.getRequestURI());
		error.setDefaultMessage(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	
	
}
