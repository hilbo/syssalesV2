package com.system.syssalesv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Telephone;
import com.system.syssalesv2.repositories.TelephoneRepository;


@Service
public class TelephoneService {
	@Autowired
	TelephoneRepository telephoneRepository;
		
	public List<Telephone> findAll(){
		return telephoneRepository.findAll();
	}
	
	public Telephone save(Telephone telephone) {
		return telephoneRepository.save(telephone);
	}
}
