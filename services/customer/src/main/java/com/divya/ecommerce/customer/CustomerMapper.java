package com.divya.ecommerce.customer;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CustomerMapper {


	public Customer toCustomer(@Valid CustomerRequest request) {
		// TODO Auto-generated method stub
		
		if(request==null) {
			
			return null;
		}
		
		return Customer.builder()
				   .id(request.id())
				   .firstName(request.firstName())
				   .lastName(request.lastName())
				   .email(request.email())
				   .address(request.address())
				   .build();
				
	}
	
	public CustomerResponse fromCustomer(@Valid Customer customer) {
		
		return new CustomerResponse(
				customer.getId(),
				customer.getFirstName(),
				customer.getLastName(),
				customer.getEmail(),
				customer.getAddress()
			);
				  
				
	}

}
