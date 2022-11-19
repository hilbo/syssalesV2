package com.system.syssalesv2.validatories.implementations;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderInserDTO;
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
	public void validOrderInsert(OrderInserDTO orderInsertDto) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Cliente não pode ser nulo valid !");
		errorTmp.setCodInternal(300);
		errorTmp.setStatus(300);
		errorTmp.setError("Cliente não pode ser nulo valid !");
		errorTmp.setField("order.client");
				
		if (orderInsertDto.getClientId() == null) {
			error.getErros().add(errorTmp);
		}
		
		SpecificException errorTmp2 = new SpecificException();
		errorTmp2.setDefaultMessage("Pagamento não pode ser nulo !");
		errorTmp2.setCodInternal(334300);
		errorTmp2.setStatus(334300);
		errorTmp2.setError("Pagamento não pode ser nulo !");
		errorTmp2.setField("order.payments");
				
		if (orderInsertDto.getPayments().isEmpty()) {
			error.getErros().add(errorTmp2);
		}
		
		valid();
				
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
		return error;
	}

	@Override
	public void setError(StandardException error) {
		this.error = error;
	}

	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationException("Erro de validação !");
		}
	}

	
}
