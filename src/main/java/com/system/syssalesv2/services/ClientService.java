package com.system.syssalesv2.services;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.entities.enums.TypeClient;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.Validation;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;

	public Client save(Client client) {
		Validation validator = new Validation();
		try {
			validator.validBlanck(client.getName());
			if (client.getTypeClient().equals(TypeClient.PESSOAFISICA)) {
				validator.validCPF(client.getCpfOrCnpj());
			}
			if (client.getTypeClient().equals(TypeClient.PESSOAJURIDICA)) {
				validator.validCNPJ(client.getCpfOrCnpj());
			}

			validator.valid();

			return clientRepository.save(client);

		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		}

	}

	public Client findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (Exception e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !");
		}
	}
}
