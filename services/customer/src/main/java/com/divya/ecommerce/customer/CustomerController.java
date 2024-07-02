package com.divya.ecommerce.customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

	
	private final CustomerService service;
	
	@PostMapping
	public ResponseEntity<String> createCustomer(
			@RequestBody @Valid CustomerRequest request){
				
		//ResponseEntity.created();(service.createCustomer(request));
		return ResponseEntity.ok(service.createCustomer(request)); 
		
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> findAll(){
		
		return ResponseEntity.ok(service.findAllCustomers());
	}
	
	@PutMapping
	public ResponseEntity<Void> updateCustomer(
			@RequestBody @Valid CustomerRequest request){
		
		    service.updateCustomer(request);
		   
			return ResponseEntity.accepted().build();
	}
	
	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsById(
		@PathVariable("id") String id){
		
		return ResponseEntity.ok(service.existsById(id));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponse> findById(
		@PathVariable("id") String id){
		
		return ResponseEntity.ok(service.findById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCustomer(
			@PathVariable("id") String id){
		
		service.deleteCustomer(id);
		return ResponseEntity.accepted().build();
	}
}
