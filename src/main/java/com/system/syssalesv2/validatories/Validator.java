package com.system.syssalesv2.validatories;

import com.system.syssalesv2.DTO.OrderDTO;
import com.system.syssalesv2.DTO.PaymentDTO;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.services.PaymentService;
import com.system.syssalesv2.services.ProductService;

public interface Validator {
	StandardException getError();
	void setError(StandardException error);
	void validCPF(String value, String field);
	void validCNPJ(String value, String field);
	void validBlanck(String value, String field);
	void validEmail(String value, String field);
	void validType(Integer value, String field);
	void validTelephone(String value, String field);
	void validEmailReapt(String value, String field, ClientRepository clientRepository);
	void notNullEntite(Object obj, String field);
	void notNull(String value, String field);
	void aboveZero(Integer value, String field);
	void validOrder(OrderDTO orderInsertDto, ProductService productService, PaymentService paymentService);
	void validOrderItem(OrderItem orderItem, ProductService productService);
	void validPayment(Payment payment);
	void validPaymentOrder(Order order);
	void validPaymentWithCard(PaymentDTO paymentDTO);
	void valid();
	
	
	
}
