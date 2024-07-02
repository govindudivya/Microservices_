package com.divya.ecommerce.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
		
		@NotNull
		Integer productId,

		@Positive(message = "must be >0")
		 double quantity

		
		) {

}
