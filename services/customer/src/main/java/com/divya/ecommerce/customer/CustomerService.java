package com.divya.ecommerce.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository repo;
	private final CustomerMapper mapper;

	public String createCustomer(@Valid CustomerRequest request) {
		// TODO Auto-generated method stub
		var customer= repo.save(mapper.toCustomer(request));
		return customer.getId();
	}

	public List<CustomerResponse> findAllCustomers() {
		// TODO Auto-generated method stub
		
		return repo.findAll()
			   .stream()
			   .map(mapper::fromCustomer)
			   .collect(Collectors.toList());
			
	}

	public void updateCustomer(CustomerRequest request) {
		// TODO Auto-generated method stub
		
		var customer  = repo.findById(request.id())
						.orElseThrow(()-> new CustomerNotFoundException(
								String.format("customer with id %s is not present", request.id())));
		mergeCustomer(customer,request);
		
	}

	private void mergeCustomer(Customer customer, CustomerRequest request) {
		// TODO Auto-generated method stub
		if(request.firstName()!=null)
			customer.setFirstName(request.firstName());
		
		if(request.lastName()!=null)
			customer.setLastName(request.lastName());
		
		if(request.email()!=null)
			customer.setEmail(request.email());
		
		
		if(request.address()!=null)
			customer.setAddress(request.address());
	}

	public Boolean existsById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).isPresent();
		
	}

	public void deleteCustomer(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	public CustomerResponse findById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id)
				.map(mapper::fromCustomer)
				.orElseThrow(()-> new CustomerNotFoundException(
						String.format("customer with id %s is not present",id)));
						
		
	}

}
