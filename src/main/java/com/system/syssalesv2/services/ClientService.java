package com.system.syssalesv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public Client findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (Exception e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !");
		}
	}
}
