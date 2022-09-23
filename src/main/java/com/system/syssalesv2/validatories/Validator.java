package com.system.syssalesv2.validatories;

public interface Validator {
	public Boolean validCPF(String value);
	public Boolean validCNPJ(String value);
	public Boolean validBlanck(String value);

}
