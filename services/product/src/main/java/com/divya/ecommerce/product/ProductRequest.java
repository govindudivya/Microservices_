package com.divya.ecommerce.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
		
		Integer id,
		@NotNull(message="Name cannot be null")
		 String name,
		String description,
		@Positive(message="Quantity Should be >0")
		 double availableQuantity,
		 @Positive(message="Quantity Should be >0")
		 BigDecimal price,
		 @NotNull(message="Category Id  cannot be null")
		 Integer category_id
		
		) {

}
