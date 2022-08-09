package com.system.syssalesv2.configurations;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.entities.Client;
import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.entities.State;
import com.system.syssalesv2.entities.Telephone;
import com.system.syssalesv2.entities.enums.TypeClient;
import com.system.syssalesv2.services.AddressService;
import com.system.syssalesv2.services.CategoryService;
import com.system.syssalesv2.services.CityService;
import com.system.syssalesv2.services.ClientService;
import com.system.syssalesv2.services.ProductService;
import com.system.syssalesv2.services.StateService;
import com.system.syssalesv2.services.TelephoneService;

@Configuration
public class Cid implements CommandLineRunner {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CityService cityService;
	@Autowired
	private StateService stateService;
	@Autowired
	private TelephoneService telephoneService;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Instanceament de Producties, Categories and associations.
		Product product01 = new Product(null, "Produto01", 200.0);
		productService.save(product01);
		Product product02 = new Product(null, "Produto02", 300.0);
		productService.save(product02);
		Product product03 = new Product(null, "Produto02", 400.0);
		productService.save(product03);
		
		Category category01 = new Category(null, "Category01");
		categoryService.save(category01);
		Category category02 = new Category(null, "Category02");
		categoryService.save(category02);
		
		category01.getProducties().addAll(Arrays.asList(product01, product02, product03));
		categoryService.save(category01);
		category02.getProducties().add(product02);
		categoryService.save(category02);
		
		State state01 = new State(null, "SP");
		stateService.save(state01);
		State state02 = new State(null, "RJ");
		stateService.save(state02);
		
		City city01 = new City(null, "Diadema", state01);
		cityService.save(city01);
		City city02 = new City(null, "São Paulo", state01);
		cityService.save(city02);
			
		Address address01 = new Address(null, "Rua Epiceia", 78, "Casa 1", "Jardim Ana Maria", "09931340", city01);
		addressService.save(address01);
		Address address02 = new Address(null, "Rua Epiceia", 100, "Casa 2", "Jardim Ana Maria", "09931340", city02);
		addressService.save(address02);
		
		Telephone telephone01 = new Telephone(null, "46818842");
		Telephone telephone02 = new Telephone(null, "94600999");
		telephoneService.save(telephone01);
		telephoneService.save(telephone02);
		
		Client client01 = new Client(null, "Cliente01", "cliente01@email.com", "cpf", TypeClient.PESSOAJURIDICA);
		client01.getAddresses().add(address01);
		//client01.getAddresses().add(address02);
		client01.getTelephones().addAll(Arrays.asList(telephone01, telephone02));
		clientService.save(client01);
		
		Client client02 = new Client(null, "Cliente02", "cliente01@email.com", "cpf", TypeClient.PESSOAJURIDICA);
		client02.getAddresses().add(address02);
		//client01.getAddresses().add(address02);
		clientService.save(client02);
		
		
		
	}
}
