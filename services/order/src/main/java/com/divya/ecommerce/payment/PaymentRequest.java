package com.divya.ecommerce.payment;

import java.math.BigDecimal;

import com.divya.ecommerce.customer.CustomerResponse;
import com.divya.ecommerce.order.PaymentMethod;


public record PaymentRequest(
		
		BigDecimal amount,
		PaymentMethod paymentMethod,
		Integer orderId,
		String orderReference,
		CustomerResponse customer
		
		) {

}
