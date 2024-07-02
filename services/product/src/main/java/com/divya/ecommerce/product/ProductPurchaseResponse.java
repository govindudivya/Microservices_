package com.divya.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
		
		Integer id,

		 String name,
		String description,

		 double availableQuantity,

		 BigDecimal price
		
		) {

}
