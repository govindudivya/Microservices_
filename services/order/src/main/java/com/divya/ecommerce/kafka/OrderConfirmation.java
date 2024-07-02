package com.divya.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

import com.divya.ecommerce.customer.CustomerResponse;
import com.divya.ecommerce.order.PaymentMethod;
import com.divya.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
		
		String orderReference,
		BigDecimal totalAmount,
		PaymentMethod paymentMethod,
		CustomerResponse customer,
		List<PurchaseResponse> products
		
		) {

}
