package com.system.syssalesv2.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.repositories.CityRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public City save(City city) {
		return cityRepository.save(city);
	}
	
	public City findById(Long id) {
		try {
			if (cityRepository.findById(id) == null) {
				throw new NoSuchElementException();
			}
			return cityRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cidade não encontrado !", "City");
		}
	}
	
	public City findByName(String name) {
		try {
			if (cityRepository.findByName(name) == null) {
				throw new NoSuchElementException();
			}
			return cityRepository.findByName(name);
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cidade não encontrado !", "City");
		}
	}
}
