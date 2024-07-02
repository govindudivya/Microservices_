package com.divya.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
		
		@NotNull(message = "product is mandatory")
		Integer productId,
		@NotNull(message="quantity is mandatory")
		double quantity
		
		) {

}
