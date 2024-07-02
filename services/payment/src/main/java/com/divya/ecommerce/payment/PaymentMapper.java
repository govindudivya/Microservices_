package com.divya.ecommerce.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

	public Payment toPayment(PaymentRequest request) {
		// TODO Auto-generated method stub
		return Payment.builder()
				.id(request.id())
				.paymentMethod(request.paymentMethod())
				.amount(request.amount())
				.orderId(request.orderId())
				.build();
	}

}
