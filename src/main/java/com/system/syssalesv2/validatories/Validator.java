package com.system.syssalesv2.validatories;

import com.system.syssalesv2.DTO.OrderInserDTO;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.StandardException;

public interface Validator {
	void validCPF(String value, String field);
	void validCNPJ(String value, String field);
	void validBlanck(String value, String field);
	void validEmail(String value, String field);
	void validType(Integer value, String field);
	void validTelephone(String value, String field);
	void validEmailReapt(String value, String field, ClientRepository clientRepository);
	void notNullEntite(Object obj, String field);
	void validOrderInsert(OrderInserDTO orderInsertDto);
	StandardException getError();
	void setError(StandardException error);
	void valid();
	
}
