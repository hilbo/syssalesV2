package com.system.syssalesv2.validatories;

import com.system.syssalesv2.resourcesExecpitions.StandardException;

public interface Validator {
	void validCPF(String value, String field);
	void validCNPJ(String value, String field);
	void validBlanck(String value, String field);
	void validEmail(String value, String field);
	void valid();
	void validType(Integer value, String field);
	public StandardException getError();
}
