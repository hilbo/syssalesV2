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
public class ValidationPayment implements Validator {
	private StandardException error = new StandardException();

	public ValidationPayment() {
	}

	@Override
	public void validPaymentWithCard(PaymentDTO paymentDTO) {
		try {
			paymentDTO.getPaymentDate();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "A data de pagamento não pode ser nula !", "Data de pagamento nula !",
					"order.orderItem.date"));
		}
		
		try {
			paymentDTO.getPaymentedValue();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O valor do pagamento não pode ser nulo !", "Valor de pagamento nulo !",
					"order.payment.PaymentedValue"));
		}
		
		try {
			paymentDTO.getPaymentState();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O estado de pagamento não pode ser nulo !", "Estado de pagamentoEstado de pagamento nulo !",
					"order.payment.state"));
		}
		
		try {
			paymentDTO.getNumberStallments();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "Número de parcelas não informado !", "Número de parcelas não informado !",
					"order.payment.numberStallments"));
		}
		
				
		valid();
		
	}
	
	@Override
	public void validPayment(Payment payment) {
		
		/*
		try {
			payment.getPaymentDate();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "A data de pagamento não pode ser nula !", "Data de pagamento nula !",
					"order.orderItem.date"));
		}
		
		
		try {
			payment.getPaymentedValue();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O valor do pagamento não pode ser nulo !", "Valor de pagamento nulo !",
					"order.payment.PaymentedValue"));
		}
		
		try {
			payment.getPaymentState();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O estado de pagamento não pode ser nulo !", "Estado de pagamentoEstado de pagamento nulo !",
					"order.payment.state"));
		}
		
		try {
			payment.getPaymentType();
		} catch (ValidationException e) {
			error.getErros().add(new SpecificException(01, 01, "O tipo de pagamento não pode ser nulo !", "Tipo de pagamentoEstado de pagamento nulo !",
					"order.payment.type"));
		}
		
		
			if (payment.getPaymentType().equals(PaymentType.CARTAO) && payment.getNumberStallments() == null) {
				error.getErros().add(new SpecificException(01, 01, "Número de parcelas não informado !", "Número de parcelas não informado !",
						"order.payment.numberStallments"));
			}
			
			if (payment.getNumberStallments() != null && payment.getNumberStallments() < 0) {
				error.getErros().add(new SpecificException(01, 01, "Número de parcelas não pode ser negativo !", "Número de parcelas negativo !",
						"order.payment.numberStallments"));
			}
			
			
			Validator validator02 = new ValidationPayment();
		for (PaymentDTO paymentDTO : orderInsertDto.getPaymentsDTO()) {
			try {
				validator02.validPayment(paymentDTO);
			} catch (ValidationExceptionService e) {
				error.getErros().addAll(e.getError().getErros());
				e.getError().getErros().clear();
			}
		}
			
			*/		
		
		valid();
	}
	
	@Override
	public void valid() {
		if (!error.getErros().isEmpty()) {
			throw new ValidationExceptionService("Erro de validação !", error);
		}
	}
	
	@Override
	public void validOrderItem(OrderItem orderItem, ProductService productService) {
	
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
	public void validPaymentOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	
}
