package com.system.syssalesv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.repositories.CityRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class CityService {
	@Autowired
	private CityRepository clientRepository;
	
	public City save(City client) {
		return clientRepository.save(client);
	}
	
	public City findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (Exception e) {
			throw new ServiceNoSuchElementException("Cidade não encontrado !");
		}
	}
}
