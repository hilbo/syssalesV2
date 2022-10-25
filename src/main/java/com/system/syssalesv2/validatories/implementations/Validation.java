package com.system.syssalesv2.validatories.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import com.system.syssalesv2.entities.enums.TypeClient;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.checkers.IsCNPJ;
import com.system.syssalesv2.validatories.checkers.IsCPF;
import com.system.syssalesv2.validatories.checkers.IsEmail;
import com.system.syssalesv2.validatories.checkers.IsTelephone;

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
		List<TypeClient> listTmp = new ArrayList<>();

		for (TypeClient typeClient : TypeClient.values()) {
			if (value.equals(typeClient.getCod()) && value != 300) {
				listTmp.add(typeClient);
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
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationException("Erro de validação !");
		}
	}

}
