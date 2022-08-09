package com.system.syssalesv2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.repositories.AddressRepository;


@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
		
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}
}
