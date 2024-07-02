package com.divya.ecommerce.notification;

import java.math.BigDecimal;

import com.divya.ecommerce.payment.PaymentMethod;

public record PaymentNotificationRequest(
		
		String orderReference,
		BigDecimal amount,
		PaymentMethod paymentMethod,
		String customerFirstName,
		String customerLastName,
		String customerEmail
		
		) {

}
