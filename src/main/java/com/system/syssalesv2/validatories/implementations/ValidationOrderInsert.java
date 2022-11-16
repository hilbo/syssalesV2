package com.system.syssalesv2.validatories.implementations;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.validatories.Validator;
@Service
public class ValidationOrderInsert implements Validator {
	private StandardException error = new StandardException();

	public ValidationOrderInsert() {
	}
	
	@Override
	public void validOrderInsert(Order order) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Cliente não pode ser nulo valid !");
		errorTmp.setCodInternal(300);
		errorTmp.setStatus(300);
		errorTmp.setError("Cliente não pode ser nulo valid !");
		//errorTmp.setField(field);
				
		if (order.getClient() == null) {
			error.getErros().add(errorTmp);
		}
		//valid();
				
	}

	@Override
	public void validCPF(String value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validCNPJ(String value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validBlanck(String value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validEmail(String value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validType(Integer value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validTelephone(String value, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validEmailReapt(String value, String field, ClientRepository clientRepository) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notNullEntite(Object obj, String field) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public StandardException getError() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationException("Erro de validação !");
		}
	}

	
}
