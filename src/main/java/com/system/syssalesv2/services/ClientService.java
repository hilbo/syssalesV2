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
import com.system.syssalesv2.entities.Address;
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
					clientDto.getComplement(), clientDto.getCityId(), clientDto.getZipCod(),
					cityService.findById(Long.parseLong(clientDto.getCityId())));
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

			validator.valid();

			save(client);
			return clientFromClientDTO(client);
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		}
	}
		
	public Page<ClientDTO> findPage(Pageable page) {
		Page<Client> pageClient = clientRepository.findAll(page);
		Page<ClientDTO> pageClientDTO = pageClient.map(x -> clientFromClientDTO(x));
		return pageClientDTO;
	}

	@Transactional
	public Client save(Client client) {
		client.setId(null);
		return clientRepository.save(client);
	}

	private ClientDTO clientFromClientDTO(Client client) {
		List<Telephone> telephones = new ArrayList<>();
		String telephone1 = "0";
		String telephone2 = "0";
		for (Telephone telephone : client.getTelephones()) {
			telephones.add(telephone);
		}
		if (!telephones.isEmpty()) {
			telephone1 = telephones.get(0).getNumber();
		}
		if (! telephones.isEmpty()) {
			telephone2 = telephones.get(1).getNumber();
		}
						
		List<Address> addresses = new ArrayList<>();
		for (Address addressTmp : client.getAddresses()) {
			addresses.add(addressTmp);
		}
		Address address2 = new Address();
		address2 = addresses.get(0);
		String address = addresses.get(0).getAddress();
		String number = addresses.get(0).getNumber().toString();
		String complement = addresses.get(0).getComplement();
		String zipCod = addresses.get(0).getZipCode();
		String cityId = cityService.findById(address2.getCity().getId()).getId().toString();
						
		ClientDTO clientDTO = new ClientDTO(client.getId()
											, client.getName()
											, client.getEmail()
											, client.getCpfOrCnpj()
											, client.getTypeClient().toString()
											, telephone1
											, telephone2
											, address
											, number
											, complement
											, zipCod
											, cityId
											);
				
		return clientDTO;
	}

	@Transactional
	public void update(Long id, Client client) {
		Client catTmp = findById(id);
		if (!client.getName().equals(null)) {
			catTmp.setName(client.getName());
		}
		clientRepository.save(catTmp);
	}

	public void delete(Long id) {
		clientRepository.delete(findById(id));
	}

}
