package com.system.syssalesv2.resourcesExecpitions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@ControllerAdvice
public class ResourceExecption {
	
	@ExceptionHandler(ServiceNoSuchElementException.class)
	public ResponseEntity<StandardException> ServiceNoSuchElementException(ServiceNoSuchElementException e, HttpServletRequest request) {
		StandardException error = new StandardException();
		SpecificException specificException = new SpecificException();
		SpecificException specificException2 = new SpecificException();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError(HttpStatus.NOT_FOUND.name());
		error.setPath(request.getRequestURI());
		error.setDefaultMessage(e.getMessage());
		
		specificException.setDefaultMessage("teste");
		specificException2.setDefaultMessage("teste2");
		
		error.getErros().add(specificException);
		error.getErros().add(specificException2);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
