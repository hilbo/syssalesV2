package com.system.syssalesv2.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

import com.system.syssalesv2.DTO.ClientInsertDTO;
import com.system.syssalesv2.DTO.ClientPageDTO;
import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.entities.Telephone;
import com.system.syssalesv2.entities.enums.ClientType;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.Validation;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CityService cityService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private TelephoneService telephoneService;

	public Client findById(Long id) {
		try {
			if (id == null) {
				throw new NoSuchElementException();
			}
			return clientRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !", "client");
		}
	}

	public Page<ClientPageDTO> findPage(Pageable page) {
		Page<Client> pageClient = clientRepository.findAll(page);
		Page<ClientPageDTO> pageClientDTOPage = pageClient.map(x -> clientFromClientPageDTO(x));
		return pageClientDTOPage;
	}

	public Page<Client> findPerEmail(String email, Pageable page) {
		try {
			if (clientRepository.findPerEmail(email, page).isEmpty()) {
				throw new NoSuchElementException();
			}
			return clientRepository.findPerEmail(email, page);
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !!", "email");
		}
	}

	public Page<Client> findByCpfOrCnpj(String cpfOrCnpj, Pageable page) {
		try {
			if (clientRepository.findByCpfOrCnpj(cpfOrCnpj, page).isEmpty()) {
				throw new NoSuchElementException();
			}
			return clientRepository.findByCpfOrCnpj(cpfOrCnpj, page);
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Cliente não encontrado !!", "cpfOrCnpj");
		}
	}

	public Page<Client> findByAttribut(String attribut, String value, Pageable page) {
		try {
			String path = "com.system.syssalesv2.entities.";
			String cls = "Client";
			Class<?> clsTmp = Class.forName(path + cls);
			clsTmp.getDeclaredConstructor().newInstance();

			List<Field> fields = new ArrayList<>();
			fields = Arrays.asList(clsTmp.getDeclaredFields());

			Page<Client> pageClient = null;

			for (Field field : fields) {
				if (field.getName().equals(attribut) && field.getName().equals("cpfOrCnpj")) {
					pageClient = findByCpfOrCnpj(value, page);
				}
				if (field.getName().equals(attribut) && field.getName().equals("email")) {
					pageClient = findPerEmail(value, page);
				}
			}

			if (pageClient == null) {
				throw new NoSuchMethodException("Atributo não encontrado !!");
			}

			return pageClient;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e1) {
			throw new RuntimeException(e1.getMessage());
		} catch (NoSuchMethodException e2) {
			throw new ServiceNoSuchElementException(e2.getMessage(), attribut);
		}
	}

	@Transactional
	public Client save(Client client) {
		client.setId(null);
		return clientRepository.save(client);
	}

	@Transactional
	public ClientInsertDTO saveDTO(ClientInsertDTO clientDto) {
		Validator validator = new Validation();
		try {
			Address address = new Address(null, clientDto.getAddress(), Integer.parseInt(clientDto.getNumber()),
					clientDto.getComplement(), "", clientDto.getZipCod(),
					cityService.findById(Long.parseLong(clientDto.getCityId())));
			addressService.save(address);

			validator.validBlanck(clientDto.getTelephone1(), "telephone1");
			validator.validTelephone(clientDto.getTelephone1(), "telephone1");
			Telephone telephone1 = new Telephone(null, clientDto.getTelephone1());
			validator.validBlanck(clientDto.getTelephone1(), "telephone2");
			validator.validTelephone(clientDto.getTelephone2(), "telephone2");
			Telephone telephone2 = new Telephone(null, clientDto.getTelephone2());
			telephoneService.save(telephone1);
			telephoneService.save(telephone2);

			Client client = new Client();

			validator.validBlanck(clientDto.getName(), "name");
			client.setName(clientDto.getName());

			validator.validBlanck(clientDto.getEmail(), "email");
			validator.validEmail(clientDto.getEmail(), "email");
			validator.validEmailReapt(clientDto.getEmail(), "email", clientRepository);
			client.setEmail(clientDto.getEmail());

			validator.validBlanck(clientDto.getType(), "typeClient");
			validator.validType(Integer.parseInt(clientDto.getType()), "typeClient");
			client.setTypeClient(ClientType.typeClientToEnum(Integer.parseInt(clientDto.getType())));

			if (Integer.parseInt(clientDto.getType()) == ClientType.PESSOAFISICA.getCod()) {
				validator.validBlanck(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				validator.validCPF(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDto.getCpfOrCnpj());
			}
			if (Integer.parseInt(clientDto.getType()) == ClientType.PESSOAJURIDICA.getCod()) {
				validator.validBlanck(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				validator.validCNPJ(clientDto.getCpfOrCnpj(), "cpfOrCnpj");
				client.setCpfOrCnpj(clientDto.getCpfOrCnpj());
			}

			client.getAddresses().add(address);
			client.getTelephones().addAll(Arrays.asList(telephone1, telephone2));

			address.setClient(client);
			addressService.save(address);

			validator.valid();

			save(client);

			return clientFromClientInsertDTO(client);
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
		}
	}

	private ClientPageDTO clientFromClientPageDTO(Client client) {
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

		ClientPageDTO clientPageDTO = new ClientPageDTO(client.getId(), client.getName(), client.getEmail(),
				client.getCpfOrCnpj(), client.getTypeClient().toString(), telephone1, telephone2, address, number,
				complement, zipCod, city);

		return clientPageDTO;
	}

	private ClientInsertDTO clientFromClientInsertDTO(Client client) {
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

		ClientInsertDTO clientInsertDTO = new ClientInsertDTO(client.getId(), client.getName(), client.getEmail(),
				client.getCpfOrCnpj(), client.getTypeClient().toString(), telephone1, telephone2, address, number,
				complement, zipCod, cityId);

		return clientInsertDTO;
	}

	@Transactional
	public void update(Long id, Client obj) {
		Client objTmp = findById(id);
		objTmp = updatePrep(objTmp, obj);
		clientRepository.save(objTmp);
	}

	private Client updatePrep(Client objTmp, Client obj) {
		Validator validator = new Validation();
		try {
			validator.validBlanck(obj.getName(), "name");
			objTmp.setName(obj.getName());

			for (Client client : findPerEmail(obj.getEmail(), null)) {
				if (client != null && client.equals(objTmp)) {
					objTmp.setEmail(obj.getEmail());
				}
				if (client != null && !client.equals(objTmp)) {
					validator.validEmailReapt(obj.getEmail(), "email", clientRepository);
				}
			}
			validator.valid();
		} catch (ValidationException e) {
			throw new ValidationExceptionService(e.getMessage(), validator.getError());
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

	private String testNull(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/*
	 * public boolean emailExist(String email) { List<Client> clienties = new
	 * ArrayList<>(); clienties.addAll(clientRepository.findPerEmail(email)); if
	 * (clienties.isEmpty()) { return false; } return true; }
	 */
}
