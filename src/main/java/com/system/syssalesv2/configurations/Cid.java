package com.system.syssalesv2.configurations;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.system.syssalesv2.DTO.ClientInsertDTO;
import com.system.syssalesv2.entities.Address;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.City;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.entities.PaymentWithCard;
import com.system.syssalesv2.entities.PaymentWithTicket;
import com.system.syssalesv2.entities.Product;
import com.system.syssalesv2.entities.State;
import com.system.syssalesv2.entities.Telephone;
import com.system.syssalesv2.entities.enums.StatePayment;
import com.system.syssalesv2.repositories.OrderItemRepository;
import com.system.syssalesv2.services.AddressService;
import com.system.syssalesv2.services.CategoryService;
import com.system.syssalesv2.services.CityService;
import com.system.syssalesv2.services.ClientService;
import com.system.syssalesv2.services.OrderService;
import com.system.syssalesv2.services.PaymentService;
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
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Instanceament de Producties, Categories and associations.
		Product product01 = new Product(null, "Produto01", 100.0);
		productService.save(product01);
		Product product02 = new Product(null, "Produto01", 300.0);
		productService.save(product02);
		Product product03 = new Product(null, "Produto03", 400.0);
		productService.save(product03);
		
		Category category01 = new Category(null, "Category01");
		categoryService.save(category01);
		Category category02 = new Category(null, "Category02");
		categoryService.save(category02);
				
		product01.getCategories().add(category01);
		product02.getCategories().add(category02);
		product03.getCategories().add(category01);
		
		productService.saveAll(Arrays.asList(product01, product02, product03));
		
		State state01 = new State(null, "SP");
		stateService.save(state01);
		State state02 = new State(null, "RJ");
		stateService.save(state02);
		
		City city01 = new City(null, "Diadema", state01);
		cityService.save(city01);
		City city02 = new City(null, "São Paulo", state02);
		cityService.save(city02);
					
		Address address01 = new Address(null, "Rua Epiceia", 78, "Casa 1", "Jardim Ana Maria", "09931340", city01);
		addressService.save(address01);
		Address address02 = new Address(null, "Rua Epiceia", 100, "Casa 2", "Jardim Ana Maria", "09931340", city01);
		addressService.save(address02);
		
		Telephone telephone01 = new Telephone(null, "119468188422");
		Telephone telephone02 = new Telephone(null, "119460099941");
		telephoneService.save(telephone01);
		telephoneService.save(telephone02);
		
		ClientInsertDTO clientDTO01 = new ClientInsertDTO(null, "Client01", "client01@client01.com", "15357450889", "100", telephone01.getNumber(), telephone02.getNumber(), "Rua 1", "1", "Complemento", "09931340", city01.getId().toString());
		clientDTO01 = clientService.saveDTO(clientDTO01);
		
		ClientInsertDTO clientDTO02 = new ClientInsertDTO(null, "Client02", "client02@client02.com", "05396440000123", "200", telephone01.getNumber(), telephone02.getNumber(), "Rua 1", "1", "Complemento", "09931340", city02.getId().toString());
		clientDTO02 = clientService.saveDTO(clientDTO02);
		
		Order order01 = new Order(null, LocalDateTime.now(), clientService.findById(clientDTO01.getId()), address02);
		orderService.save(order01);
		
		Order order02 = new Order(null, LocalDateTime.now(), clientService.findById(clientDTO02.getId()), address02);
		orderService.save(order02);
		
		Payment pay01 = new PaymentWithCard(null, StatePayment.PENDENTE, 2, order01);
		paymentService.save(pay01);
		
		Payment pay02 = new PaymentWithTicket(null, StatePayment.PENDENTE, LocalDateTime.now(), order02);
		paymentService.save(pay02);
		
		OrderItem orderItem01 = new OrderItem(null, order01, 0.0, 2, product01);
		OrderItem orderItem02 = new OrderItem(null, order01, 10.0, 1, product01);
		OrderItem orderItem03 = new OrderItem(null, order01, 50.0, 10, product01);
		
		orderItemRepository.save(orderItem01);
		orderItemRepository.save(orderItem02);
		orderItemRepository.save(orderItem03);
		
		order01.getOrderItens().add(orderItem01);
		order01.getOrderItens().add(orderItem02);
		order01.getOrderItens().add(orderItem03);
		orderService.save(order01);
		
	}
}
