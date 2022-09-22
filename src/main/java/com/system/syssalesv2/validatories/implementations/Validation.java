package com.system.syssalesv2.validatories.implementations;

import javax.validation.ValidationException;

import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.checkers.IsCNPJ;
import com.system.syssalesv2.validatories.checkers.IsCPF;

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
