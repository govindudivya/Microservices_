package com.divya.ecommerce.order;

import java.math.BigDecimal;
import java.util.List;

import com.divya.ecommerce.product.PurchaseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
		
		Integer id,
		String reference,
		@Positive(message = "Amount must be +")
		BigDecimal amount,
		PaymentMethod paymentMethod,
		@NotNull(message ="it can't be null")
		@NotEmpty(message = "it can't be empty")
		@NotBlank(message = "it can't be empty")
		String customerId,
		@NotEmpty(message="Atleast 1 product should be present")
		List<PurchaseRequest> products
		
		) {

}
