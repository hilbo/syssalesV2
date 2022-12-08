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
public class ValidationOrderItem implements Validator {
	private StandardException error = new StandardException();

	public ValidationOrderItem() {
	}

	@Override
	public void validOrderItem(OrderItem orderItem, ProductService productService) {
		try {
			orderItem.getQuantity();

		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "A quantidade não pode ser nulo !", "Quantidade nula !",
					"order.orderItem.quantity"));

		}

		try {
			orderItem.getDiscount();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O desconto não pode ser nulo !", "Desconto nulo !",
					"order.orderItem.discount"));
		}

		try {
			orderItem.getProduct();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O produto não pode ser nulo !", "Produto nulo !",
					"order.orderItem.product"));
		}
		
		try {
			Integer quant = 0;
			try {
				orderItem.getQuantity();
			} catch (ValidationException e) {
				quant = null;
			}

			Integer prod = 0;
			try {
				productService.findById(orderItem.getProduct().getId());
			} catch (ValidationException e) {
				prod = null;
			}

			if (quant != null && prod != null && productService.findById(orderItem.getProduct().getId())
					.getQuantityStock() < orderItem.getQuantity()) {
				throw new ValidationException();
			}

		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(0123, 0123, "Não há quantidade em estoque !",
					"quantidade em estoque insuficiente !", "order.orderItem.quantity"));
		}
		
		

		valid();

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
	public void validOrder(OrderDTO orderInsertDto, ProductService productService, PaymentService paymentService) {
		// TODO Auto-generated method stub
		
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
