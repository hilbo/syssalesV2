package com.system.syssalesv2.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.ClientDTO;
import com.system.syssalesv2.DTO.ClientDTOPage;
import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.entities.Telephone;
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
	@Autowired
	CityService cityService;
	@Autowired
	AddressService addressService;
	@Autowired
	TelephoneService telephoneService;

	public Client findById(Long id) {
		try {
			return clientRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !");
		}
	}

	@Transactional
	public ClientDTO saveDTO(ClientDTO clientDto) {
		Validator validator = new Validation();
		try {
			Address address = new Address(null, clientDto.getAddress(), Integer.parseInt(clientDto.getNumber()),
					clientDto.getComplement(), "", clientDto.getZipCod(), cityService.findById(Long.parseLong(clientDto.getCityId())));
			addressService.save(address);

			Telephone telephone1 = new Telephone(null, clientDto.getTelephone1());
			Telephone telephone2 = new Telephone(null, clientDto.getTelephone2());
			telephoneService.save(telephone1);
			telephoneService.save(telephone2);

			Client client = new Client();

			validator.validBlanck(clientDto.getName(), "name");
			client.setName(clientDto.getName());

			validator.validBlanck(clientDto.getEmail(), "email");
			validator.validEmail(clientDto.getEmail(), "email");
			client.setEmail(clientDto.getEmail());

			validator.validType(Integer.parseInt(clientDto.getType()), "typeClient");
			client.setTypeClient(TypeClient.typeClientToEnum(Integer.parseInt(clientDto.getType())));

			if (Integer.parseInt(clientDto.getType()) == TypeClient.PESSOAFISICA.getCod()) {
				validator.validBlanck(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				validator.validCPF(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDto.getCpfOrCnpj());
			}
			if (Integer.parseInt(clientDto.getType()) == TypeClient.PESSOAJURIDICA.getCod()) {
				validator.validCNPJ(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDto.getCpfOrCnpj());
			}

			client.getAddresses().add(address);
			client.getTelephones().addAll(Arrays.asList(telephone1, telephone2));
			
			address.setClient(client);
			addressService.save(address);

			validator.valid();

			save(client);
			return clientFromClientDTO(client);
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		}
	}

	public Page<ClientDTOPage> findPage(Pageable page) {
		Page<Client> pageClient = clientRepository.findAll(page);
		Page<ClientDTOPage> pageClientDTOPage = pageClient.map(x -> clientFromClientDTOPage(x));
		return pageClientDTOPage;
	}

	private ClientDTOPage clientFromClientDTOPage(Client client) {
		List<Telephone> telephones = new ArrayList<>();
		String telephone1 = "";
		String telephone2 = "";
		for (Telephone telephone : client.getTelephones()) {
			telephones.add(telephone);
		}
		if (!telephones.isEmpty()) {
			telephone1 = telephones.get(0).getNumber();
		}
		if (!telephones.isEmpty()) {
			telephone2 = telephones.get(1).getNumber();
		}

		List<Address> addresses = new ArrayList<>();
		for (Address addressTmp : client.getAddresses()) {
			addresses.add(addressTmp);
		}
		Address addressP0 = new Address();
		if (!addresses.isEmpty()) {
			addressP0 = addresses.get(0);
		}
		String address = testNull(addressP0.getAddress());
		String number = "";
		if (addressP0.getNumber() != null) {
			number = addressP0.getNumber().toString();
		}
		String complement = testNull(addressP0.getComplement());
		String zipCod = testNull(addressP0.getZipCode());
				
		City city = new City();
				
		if (addressP0.getCity() != null) {
			city = addressP0.getCity();
		}
				
		ClientDTOPage clientDTOPage = new ClientDTOPage(client.getId(), client.getName(), client.getEmail(), client.getCpfOrCnpj(),
				client.getTypeClient().toString(), telephone1, telephone2, address, number, complement, zipCod, city);

		return clientDTOPage;
	}

	@Transactional
	public Client save(Client client) {
		client.setId(null);
		return clientRepository.save(client);
	}

	private ClientDTO clientFromClientDTO(Client client) {
		List<Telephone> telephones = new ArrayList<>();
		String telephone1 = "";
		String telephone2 = "";
		for (Telephone telephone : client.getTelephones()) {
			telephones.add(telephone);
		}
		if (!telephones.isEmpty()) {
			telephone1 = telephones.get(0).getNumber();
		}
		if (!telephones.isEmpty()) {
			telephone2 = telephones.get(1).getNumber();
		}

		List<Address> addresses = new ArrayList<>();
		for (Address addressTmp : client.getAddresses()) {
			addresses.add(addressTmp);
		}
		Address addressP0 = new Address();
		if (!addresses.isEmpty()) {
			addressP0 = addresses.get(0);
		}
		String address = testNull(addressP0.getAddress());
		String number = "";
		if (addressP0.getNumber() != null) {
			number = addressP0.getNumber().toString();
		}
		String complement = testNull(addressP0.getComplement());
		String zipCod = testNull(addressP0.getZipCode());
		String cityId = "";
		if (addressP0.getCity() != null) {
			cityId = addressP0.getCity().getId().toString();
		}
				
		ClientDTO clientDTO = new ClientDTO(client.getId(), client.getName(), client.getEmail(), client.getCpfOrCnpj(),
				client.getTypeClient().toString(), telephone1, telephone2, address, number, complement, zipCod, cityId);

		return clientDTO;
	}
	
	private String testNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	@Transactional
	public void update(Long id, Client obj) {
		Client objTmp = findById(id);
		objTmp = updatePrep(objTmp, obj);
		clientRepository.save(objTmp);
	}

	private Client updatePrep(Client objTmp, Client obj) {
		if (!obj.getName().equals(null)) {
			objTmp.setName(obj.getName());
		}
		return objTmp;
	}

	@Transactional
	public void delete(Long id) {
		Client obj = findById(id);
		clientRepository.delete(obj);
		deletePrep(obj);
	}
	
	private void deletePrep(Client obj) {
		for (Telephone objTmp : obj.getTelephones()) {
			if (!objTmp.equals(null)) {
				telephoneService.delete(objTmp.getId());
			}
		}
		
		for (Address objTmp : obj.getAddresses()) {
			if (!objTmp.equals(null)) {
				addressService.delete(objTmp.getId());
			}
		}
		
		
	}

	public Client clientDTOPageFromClient(ClientDTOPage clientDtoPage) {
		Validator validator = new Validation();
		try {
			Address address = new Address(null, clientDtoPage.getAddress(), Integer.parseInt(clientDtoPage.getNumber()),
					clientDtoPage.getComplement(), "", clientDtoPage.getZipCod(), cityService.findById(Long.parseLong(clientDtoPage.getCity())));
			addressService.save(address);

			Telephone telephone1 = new Telephone(null, clientDtoPage.getTelephone1());
			Telephone telephone2 = new Telephone(null, clientDtoPage.getTelephone2());
			telephoneService.save(telephone1);
			telephoneService.save(telephone2);

			Client client = new Client();

			validator.validBlanck(clientDtoPage.getName(), "name");
			client.setName(clientDtoPage.getName());

			validator.validBlanck(clientDtoPage.getEmail(), "email");
			validator.validEmail(clientDtoPage.getEmail(), "email");
			client.setEmail(clientDtoPage.getEmail());

			validator.validType(Integer.parseInt(clientDtoPage.getType()), "typeClient");
			client.setTypeClient(TypeClient.typeClientToEnum(Integer.parseInt(clientDtoPage.getType())));

			if (Integer.parseInt(clientDtoPage.getType()) == TypeClient.PESSOAFISICA.getCod()) {
				validator.validBlanck(clientDtoPage.getCpfOrCnpj(), "cpfOrCnpj");
				validator.validCPF(clientDtoPage.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDtoPage.getCpfOrCnpj());
			}
			if (Integer.parseInt(clientDtoPage.getType()) == TypeClient.PESSOAJURIDICA.getCod()) {
				validator.validCNPJ(clientDtoPage.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDtoPage.getCpfOrCnpj());
			}

			client.getAddresses().add(address);
			client.getTelephones().addAll(Arrays.asList(telephone1, telephone2));
			
			address.setClient(client);
			addressService.save(address);

			validator.valid();

			save(client);
			return client;
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		}
		
	}

}
