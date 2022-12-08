package com.system.syssalesv2.validatories.implementations;

import javax.validation.ValidationException;

import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.OrderDTO;
import com.system.syssalesv2.DTO.PaymentDTO;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.OrderItem;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.repositories.ClientRepository;
import com.system.syssalesv2.resourcesExecpitions.SpecificException;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.services.PaymentService;
import com.system.syssalesv2.services.ProductService;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;

@Service
public class ValidationOrder implements Validator {
	private StandardException error = new StandardException();

	public ValidationOrder() {
	}

	@Override
	public void validOrder(OrderDTO orderDto, ProductService productService, PaymentService paymentService) {
		try {
			orderDto.getClientId();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(235555, 235555, "Cliente não pode ser nulo valid !",
					"Cliente nulo !", "order.client"));
		}

		try {
			orderDto.getDeliveryAddressId();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(30033, 30033, "Endereço de entrega não pode ser nulo valid !",
					"endereço nulo !", "order.deliveryAddress"));
		}

		if (orderDto.getOrderItens().isEmpty()) {
			error.getErros().add(new SpecificException(222222, 2222222, "Item de pedido não pode ser nulo valid !",
					"Item de pedido nulo !", "order.orderItens"));
		}

		Validator validatorOrderItem = new ValidationOrderItem();
		for (OrderItem orderItem : orderDto.getOrderItens()) {
			try {
				validatorOrderItem.validOrderItem(orderItem, productService);
			} catch (ValidationExceptionService e) {
				error.getErros().addAll(e.getError().getErros());
				e.getError().getErros().clear();
			}
		}
				
		valid();
	}

	@Override
	public void validPaymentOrder(Order order) {
		if (!order.getPayments().isEmpty()) {
			Double paymentAmount = 0.0;
			Double orderAmount = order.getAmount();

			for (Payment payment : order.getPayments()) {
				paymentAmount = paymentAmount + payment.getPaymentValue();
			}

			if (!paymentAmount.equals(orderAmount)) {
				error.getErros().add(new SpecificException(222222, 2222222,
								"Valor de pagamento diferente do valor total do pedido !",
								"Valor de pagamento diferente de pedido !", ""));
			}
			valid();
		}
	}

	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationExceptionService("Erro de validação !", error);
		}
	}

	@Override
	public void validCPF(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validCNPJ(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validBlanck(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validEmail(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validType(Integer value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validTelephone(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validEmailReapt(String value, String field, ClientRepository clientRepository) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notNullEntite(Object obj, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public StandardException getError() {
		return error;
	}

	@Override
	public void setError(StandardException error) {
		this.error = error;
	}

	@Override
	public void aboveZero(Integer value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notNull(String value, String field) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validOrderItem(OrderItem orderItem, ProductService productService) {

	}

	@Override
	public void validPayment(Payment payment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validPaymentWithCard(PaymentDTO paymentDTO) {
		// TODO Auto-generated method stub
		
	}

}
