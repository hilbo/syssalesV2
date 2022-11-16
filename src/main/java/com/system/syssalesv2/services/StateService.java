package com.system.syssalesv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.State;
import com.system.syssalesv2.repositories.StateRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class StateService {
	@Autowired
	private StateRepository clientRepository;
	
	public State save(State client) {
		return clientRepository.save(client);
	}
	
	public State findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (Exception e) {
			throw new ServiceNoSuchElementException("Cidade não encontrado !", "State");
		}
	}
}
