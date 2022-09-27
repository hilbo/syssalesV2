package com.system.syssalesv2.validatories;

public interface Validator {
	void validCPF(String value);
	void validCNPJ(String value);
	void validBlanck(String value);
	void valid();
}
