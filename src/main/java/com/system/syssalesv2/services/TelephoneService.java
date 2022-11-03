package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Telephone;
import com.system.syssalesv2.repositories.TelephoneRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;


@Service
public class TelephoneService {
	@Autowired
	private TelephoneRepository telephoneRepository;
		
	public List<Telephone> findAll(){
		return telephoneRepository.findAll();
	}
	
	public Telephone findById(Long id) {
		try {
			return telephoneRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Telefone não encontrado !");
		}
	}
	
	@Transactional
	public Telephone save(Telephone telephone) {
		return telephoneRepository.save(telephone);
	}
	
	public void delete(Long id) {
		Telephone obj = findById(id);
		telephoneRepository.delete(obj);
	}
}
