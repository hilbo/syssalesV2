package com.system.syssalesv2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.syssalesv2.DTO.PaymentDTO;
import com.system.syssalesv2.entities.Order;
import com.system.syssalesv2.entities.Payment;
import com.system.syssalesv2.entities.PaymentWithCard;
import com.system.syssalesv2.entities.PaymentWithTicket;
import com.system.syssalesv2.entities.enums.PaymentType;
import com.system.syssalesv2.repositories.PaymentRepository;
import com.system.syssalesv2.resourcesExecpitions.StandardException;
import com.system.syssalesv2.serviceExecptions.ServiceNoSuchElementException;
import com.system.syssalesv2.validatories.Validator;
import com.system.syssalesv2.validatories.execptions.ValidationExceptionService;
import com.system.syssalesv2.validatories.implementations.ValidationPayment;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	public List<Payment> findAll() {
		return paymentRepository.findAll();
	}

	public Payment findById(Long id) {
		try {
			return paymentRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ServiceNoSuchElementException("Pagamento não encontrado !", "Payment");
		}
	}

	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Transactional
	public Payment insert(Payment payment) {
		return save(payment);
	}

	public void delete(Long id) {
		paymentRepository.delete(findById(id));

	}

	@Transactional
	public Payment mountPayment(PaymentDTO paymentDTO, Order order, StandardException error) {
		Payment payment = null;

		try {
			List<PaymentType> payTypes = new ArrayList<>();
			for (PaymentType payType : PaymentType.values()) {
				if (paymentDTO.getPaymentType().equals(payType)) {
					payTypes.add(payType);
				}
			}
			if (payTypes.isEmpty()) {
				throw new ServiceNoSuchElementException("Tipo de pagamento não encontrado !", "");
			}

			try {
				if (paymentDTO.getPaymentType().equals(PaymentType.CARTAO)) {
					Validator validator = new ValidationPayment();
					validator.validPaymentWithCard(paymentDTO);

					Payment paymentTmp = new PaymentWithCard(null, paymentDTO.getPaymentDate(),
							paymentDTO.getPaymentState(), paymentDTO.getPaymentedValue(), order,
							paymentDTO.getNumberStallments());
					payment = paymentTmp;
				}
				
				if (paymentDTO.getPaymentType().equals(PaymentType.BOLETO)) {
					Payment paymentTmp = new PaymentWithTicket(null, paymentDTO.getPaymentDate(),
							paymentDTO.getPaymentState(), paymentDTO.getPaymentedValue(), order,
							paymentDTO.getDueDate());
					payment = paymentTmp;
				}
				
				
			} catch (ValidationExceptionService e) {
				error.getErros().addAll(e.getError().getErros());
			}

			

		} catch (ServiceNoSuchElementException e) {
			throw new ServiceNoSuchElementException(e.getMessage(), "");
		}
		return payment;

	}

}
