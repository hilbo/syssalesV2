package com.system.syssalesv2.validatories.implementations;

import javax.validation.ValidationException;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.checkers.IsCNPJ;
import com.system.syssalesv2.validatories.checkers.IsCPF;

public class Validation implements Validator {

	private StandardException error = new StandardException();

	public Validation() {
	}

	public StandardException getError() {
		return error;
	}

	public void setError(StandardException error) {
		this.error = error;
	}

	@Override
	public void validBlanck(String value) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Não pode ser branco ou nulo !");
		if (value.isBlank()) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCPF(String value) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CPF inválido !");
		if (!IsCPF.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCNPJ(String value) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CNPJ inválido !");
		if (!IsCNPJ.isValid(value)) {
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
