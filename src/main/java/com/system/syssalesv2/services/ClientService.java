package com.system.syssalesv2.services;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.entities.enums.TypeClient;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.Validation;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public Client save(Client client) {
		Validator validator = new Validation();
		
		if (client.getTypeClient().equals(TypeClient.PESSOAFISICA)) {
			try {
				validator.validCPF(client.getCpfOrCnpj());
				return clientRepository.save(client);
			} catch (ValidationException e) {
				throw new ValidationExceptionService(e.getMessage());
			}
		}
		
		/*
		if (client.getTypeClient().equals(TypeClient.PESSOAJURIDICA)) {
			try {
				validator.validCNPJ(client.getCpfOrCnpj());
				return clientRepository.save(client);
			} catch (ValidationException e) {
				throw new ValidationExceptionService(e.getMessage());
			}
		}
		*/
		
		return null;
		
	}
	
	public Client findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (Exception e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !");
		}
	}
}
