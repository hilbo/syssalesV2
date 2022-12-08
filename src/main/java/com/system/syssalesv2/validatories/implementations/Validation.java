package com.system.syssalesv2.validatories.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderDTO;
import com.system.syssalesv2.DTO.PaymentDTO;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.entities.enums.ClientType;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.services.PaymentService;
import com.system.syssalesv2.services.ProductService;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.checkers.IsCNPJ;
import com.system.syssalesv2.validatories.checkers.IsCPF;
import com.system.syssalesv2.validatories.checkers.IsEmail;
import com.system.syssalesv2.validatories.checkers.IsTelephone;

@Service
public class Validation implements Validator {
	private StandardException error = new StandardException();

	public Validation() {
	}

	@Override
	public StandardException getError() {
		return error;
	}

	public void setError(StandardException error) {
		this.error = error;
	}

	@Override
	public void validBlanck(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Não pode ser branco ou nulo !");
		errorTmp.setCodInternal(200);
		errorTmp.setStatus(200);
		errorTmp.setError("Not blanck or null");
		errorTmp.setField(field);
		if (value.isBlank()) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCPF(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CPF inválido !");
		errorTmp.setCodInternal(300);
		errorTmp.setStatus(300);
		errorTmp.setError("Ivalid CPF !");
		errorTmp.setField(field);
		if (!IsCPF.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validCNPJ(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("CNPJ inválido !");
		errorTmp.setCodInternal(400);
		errorTmp.setStatus(400);
		errorTmp.setError("Ivalid CNPJ !");
		errorTmp.setField(field);
		if (!IsCNPJ.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validType(Integer value, String field) {
		SpecificException errorTmp = new SpecificException();
		List<ClientType> listTmp = new ArrayList<>();

		for (ClientType clientType : ClientType.values()) {
			if (value.equals(clientType.getCod()) && value != 300) {
				listTmp.add(clientType);
			}
		}

		if (listTmp.isEmpty()) {
			errorTmp.setDefaultMessage("Tipo de client inválido !");
			errorTmp.setCodInternal(550);
			errorTmp.setStatus(550);
			errorTmp.setError("Ivalid type client !");
			errorTmp.setField(field);
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validEmail(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Email inválido !");
		errorTmp.setCodInternal(400);
		errorTmp.setStatus(400);
		errorTmp.setError("Ivalid Email !");
		errorTmp.setField(field);
		if (!IsEmail.isValid(value)) {
			error.getErros().add(errorTmp);
		}

	}

	@Override
	public void validTelephone(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Telefone Inválido !");
		errorTmp.setCodInternal(900);
		errorTmp.setStatus(900);
		errorTmp.setError("Ivalid Telephone !");
		errorTmp.setField(field);
		if (!IsTelephone.isValid(value)) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void validEmailReapt(String value, String field, ClientRepository clientRepository) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Email já cadastrado !");
		errorTmp.setCodInternal(999);
		errorTmp.setStatus(999);
		errorTmp.setError("Email exist !");
		errorTmp.setField(field);
		if (!clientRepository.findPerEmail(value, null).isEmpty()) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void notNullEntite(Object obj, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Entidade não pode nula !");
		errorTmp.setCodInternal(999);
		errorTmp.setStatus(999);
		errorTmp.setError("notNull !");
		errorTmp.setField(field);
		if (obj == null) {
			error.getErros().add(errorTmp);
		}
	}

	@Override
	public void notNull(String value, String field) {
		SpecificException errorTmp = new SpecificException();
		errorTmp.setDefaultMessage("Valor não pode nulo !");
		errorTmp.setCodInternal(3030);
		errorTmp.setStatus(3030);
		errorTmp.setError("notNull !");
		errorTmp.setField(field);
		if (value == null) {
			error.getErros().add(errorTmp);
		}
		valid();
	}

	@Override
	public void aboveZero(Integer value, String field) {
		if (value < 1) {
			error.getErros()
					.add(new SpecificException(7400, 7400, "Valor não pode ser 0 !", "Valor não pode ser 0!", field));
		}

	}

	@Override
	public void validOrderItem(OrderItem orderItem, ProductService productService) {
		//notNullEntite(orderItem, "OrderItem");
		//valid();
		if (orderItem.getQuantity() == null) {
			error.getErros().add(new SpecificException(876, 876, "A quantidade não pode ser nula !",
					"A quantidade não pode ser nula !", "orderItem.quantity"));
		}
		if (orderItem.getDiscount() == null) {
			error.getErros().add(new SpecificException(54444, 54444, "O desconto não pode ser nulo !",
					"O desconto não pode ser nulo !", "orderItem.discount"));
		}
		//valid();
	}

	@Override
	public void validOrder(OrderDTO orderInsertDto, ProductService productService, PaymentService paymentService) {
		if (orderInsertDto.getClientId() == null) {
			error.getErros().add(new SpecificException(300, 300, "Cliente não pode ser nulo valid !",
					"Cliente não pode ser nulo valid !", "order.client"));
		}
		if (orderInsertDto.getDeliveryAddressId() == null) {
			error.getErros().add(new SpecificException(66600, 66600, "Endereço de entrega não pode ser nulo valid !",
					"Endereço de entrega não pode ser nulo valid !", "order.deliveryAddress"));
		}
		/*if (orderInsertDto.getPayments().isEmpty()) {
			error.getErros().add(new SpecificException(3900, 3800, "Pagamento não pode ser nulo  !",
					"Pagamento não pode ser nulo !", "order.payment"));
		}
		*/
		if (orderInsertDto.getOrderItens().isEmpty()) {
			error.getErros().add(new SpecificException(5555, 5555, "Item de pedido não pode ser nulo  !",
					"Item de pedido não pode ser nulo !", "order.orderItem"));
		}
		if (!orderInsertDto.getOrderItens().isEmpty()) {
			
			for ( OrderItem orderItem : orderInsertDto.getOrderItens()) {
				validOrderItem(orderItem, null);
			}
		}
	}

	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationException("Erro de validação !");
		}
	}

	@Override
	public void validPayment(Payment payment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validPaymentOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validPaymentWithCard(PaymentDTO paymentDTO) {
		// TODO Auto-generated method stub
		
	}

	
}
