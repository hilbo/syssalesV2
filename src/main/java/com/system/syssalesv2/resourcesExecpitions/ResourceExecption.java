package com.system.syssalesv2.resourcesExecpitions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.system.syssalesv2.serviceExecptions.ServiceDataIntegrityViolationException;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;

@ControllerAdvice
public class ResourceExecption {

	@ExceptionHandler(ServiceNoSuchElementException.class)
	public ResponseEntity<StandardException> ServiceNoSuchElementException(ServiceNoSuchElementException e,
			HttpServletRequest request) {
				
		StandardException error = new StandardException();
		SpecificException errors = new SpecificException();
		
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError(HttpStatus.NOT_FOUND.name());
		error.setPath(request.getRequestURI());
		error.setDefaultMessage("NOT FOUND");
		
		errors.setField(e.getField());
		errors.setCodInternal(404);
		errors.setDefaultMessage(e.getMessage());
		errors.setError(HttpStatus.NOT_FOUND.name());
		errors.setStatus(HttpStatus.NOT_FOUND.value());
        error.getErros().add(errors);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(ValidationExceptionService.class)
	public ResponseEntity<StandardException> ValidationExceptionService(ValidationExceptionService e,
			HttpServletRequest request) {
		StandardException error = new StandardException();
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError(HttpStatus.BAD_REQUEST.name());
		error.setPath(request.getRequestURI());
		error.setDefaultMessage("Erro de Validação");
		error.getErros().addAll(e.getError().getErros());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(ServiceDataIntegrityViolationException.class)
	public ResponseEntity<StandardException> ServiceDataIntegrityViolationException(
			ServiceDataIntegrityViolationException e, HttpServletRequest request) {
		StandardException error = new StandardException();

		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("BAD REQUEST");
		error.setPath(request.getRequestURI());
		error.setDefaultMessage("Item associado à outra entidade !");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardException> MethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		StandardException error = new StandardException();
		SpecificException errors = new SpecificException();

		for (FieldError objError : e.getBindingResult().getFieldErrors()) {
			errors.setError(objError.getCode());
			errors.setDefaultMessage(objError.getDefaultMessage());
			errors.setField(objError.getField());
		}

		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("BAD REQUEST");
		error.setPath(request.getRequestURI());
		error.setDefaultMessage("Erro de Validação !");
		error.getErros().add(errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<StandardException> InvalidFormatException(InvalidFormatException e,
			HttpServletRequest request) {
		StandardException error = new StandardException();
		SpecificException errors = new SpecificException();

		errors.setCodInternal(9587);
		errors.setError("Invalid Format !");
		errors.setDefaultMessage("Formato Inválido !");

		String[] str = e.getPathReference().toString().split("\"");
		String field = str[1];
		errors.setField(field);

		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("BAD REQUEST");
		error.setPath(request.getRequestURI());
		error.setDefaultMessage("BAD REQUEST");
		error.getErros().add(errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
