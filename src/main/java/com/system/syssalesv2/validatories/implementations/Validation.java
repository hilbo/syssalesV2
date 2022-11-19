package com.system.syssalesv2.validatories.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderInserDTO;
import com.system.syssalesv2.entities.enums.ClientType;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.checkers.IsCNPJ;
import com.system.syssalesv2.validatories.checkers.IsCPF;
import com.system.syssalesv2.validatories.checkers.IsEmail;
import com.system.syssalesv2.validatories.checkers.IsTelephone;
@Service
public class Validation implements Validator {
	private StandardException error = new StandardException();

	public Validation() {
	}

	@Override
	public StandardException getError() {
		return error;
	}

	public void setError(StandardException error) {
		this.error = error;
	}

	@Override
	public void validBlanck(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Não pode ser branco ou nulo !");
		errorTmp.setCodInternal(200);
		errorTmp.setStatus(200);
		errorTmp.setError("Not blanck or null");
		errorTmp.setField(field);
		if (value.isBlank()) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCPF(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CPF inválido !");
		errorTmp.setCodInternal(300);
		errorTmp.setStatus(300);
		errorTmp.setError("Ivalid CPF !");
		errorTmp.setField(field);
		if (!IsCPF.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCNPJ(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CNPJ inválido !");
		errorTmp.setCodInternal(400);
		errorTmp.setStatus(400);
		errorTmp.setError("Ivalid CNPJ !");
		errorTmp.setField(field);
		if (!IsCNPJ.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validType(Integer value, String field) {
		SpecificException errorTmp = new SpecificException();
		List<ClientType> listTmp = new ArrayList<>();

		for (ClientType clientType : ClientType.values()) {
			if (value.equals(clientType.getCod()) && value != 300) {
				listTmp.add(clientType);
			}
		}

		if (listTmp.isEmpty()) {
			errorTmp.setDefaultMessage("Tipo de client inválido !");
			errorTmp.setCodInternal(550);
			errorTmp.setStatus(550);
			errorTmp.setError("Ivalid type client !");
			errorTmp.setField(field);
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validEmail(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Email inválido !");
		errorTmp.setCodInternal(400);
		errorTmp.setStatus(400);
		errorTmp.setError("Ivalid Email !");
		errorTmp.setField(field);
		if (!IsEmail.isValid(value)) {
			error.getErros().add(errorTmp);
		}

	}

	@Override
	public void validTelephone(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Telefone Inválido !");
		errorTmp.setCodInternal(900);
		errorTmp.setStatus(900);
		errorTmp.setError("Ivalid Telephone !");
		errorTmp.setField(field);
		if (!IsTelephone.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}
	
	@Override
	public void validEmailReapt(String value, String field, ClientRepository clientRepository) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Email já cadastrado !");
		errorTmp.setCodInternal(999);
		errorTmp.setStatus(999);
		errorTmp.setError("Email exist !");
		errorTmp.setField(field);
		if (!clientRepository.findPerEmail(value, null).isEmpty()) {
			error.getErros().add(errorTmp);
		}
	}
	
	@Override
	public void notNullEntite(Object obj, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Entidade não pode nula !");
		errorTmp.setCodInternal(999);
		errorTmp.setStatus(999);
		errorTmp.setError("notNull !");
		errorTmp.setField(field);
		if (obj == null) {
			error.getErros().add(errorTmp);
		}
	}
	
	@Override
	public void validOrderInsert(OrderInserDTO orderInsertDto) {
		notNullEntite(orderInsertDto.getClientId(), "order.client");
		notNullEntite(orderInsertDto.getDeliveryAddressId(), "order.deliveryAddress");
		notNullEntite(orderInsertDto.getOrderItens(), "order.orderItens");
		if (orderInsertDto.getOrderItens().isEmpty()) {
			validBlanck("", "order.orderItens");
		}
		
	}

	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationException("Erro de validação !");
		}
	}
}
