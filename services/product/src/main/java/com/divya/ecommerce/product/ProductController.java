package com.divya.ecommerce.product;

import java.util.List;

import org.apache.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products") 
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@PostMapping
	public ResponseEntity<Integer> createProduct(
			@RequestBody @Valid ProductRequest request){
		
		return ResponseEntity.ok(service.createProduct(request));
	}
	
	@PostMapping("/purchase")
	public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
			@RequestBody @Valid List<ProductPurchaseRequest> request){
		
		return ResponseEntity.ok(service.purchaseProducts(request));
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductResponse> findById(
			@PathVariable("id") Integer id) {
		
		return ResponseEntity.ok(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}

}
