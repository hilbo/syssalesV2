package com.system.syssalesv2.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.repositories.AddressRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;


@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
		
	public List<Address> findAll(){
		return addressRepository.findAll();
	}
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public Address findById(Long id) {
		try {
			if (addressRepository.findById(id) == null) {
				throw new NoSuchElementException();
			}
			return addressRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Endereço não encontrado !", "Address");
		}
	}
	
	public void delete(Long id) {
		try {
			if (addressRepository.findById(id) == null) {
				throw new NoSuchElementException();
			}
			addressRepository.delete(findById(id));
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Endereço não encontrado !", "Address");
		}
	}
}
