package com.divya.ecommerce.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductResponse(
	
	
	Integer id,

	 String name,
	String description,

	 double availableQuantity,

	 BigDecimal price,
	
	 Integer category_id,
	 
	 String category_name,
	 
	 String Category_description
	 )
	
{
}
