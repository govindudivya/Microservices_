package com.divya.ecommerce.customer;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Validated
public record Customer(
		
		String id,
		@NotNull(message = "firstname is required")
		String firstName,
		@NotNull(message = "lastname is required")
		String lastName,
		@NotNull(message = "email is required")
		@Email(message = "customer email id is not valid")
		String email
		
		) {

}
