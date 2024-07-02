package com.divya.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
		
		 String id,
		 @NotNull(message="Name cannot be null")
		 String firstName,
		 @NotNull(message="Last Name cannot be null")
		 String lastName,
		 @NotNull(message="EMail cannot be null")
		 @Email(message="Not a valid email id")
		 String email,
		 @NotNull(message="Adress cannot be null")
		 Address address
		
		) {

}
