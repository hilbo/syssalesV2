package com.system.syssalesv2.validatories;

import javax.validation.ValidationException;

public class Validation implements Validator {

	@Override
	public Boolean validCPF(String value) {
		if (IsCPF.isValid(value) == false) {
			throw new ValidationException("CPF inválido !");
		}else {
			return true;
		}
	}

	@Override
	public Boolean validCNPJ(String value) {
		if (IsCNPJ.isValid(value) == false) {
			throw new ValidationException("CNPJ inválido !");
		}else {
			return true;
		}
	}
}
