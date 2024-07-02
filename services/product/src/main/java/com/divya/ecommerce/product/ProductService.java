package com.divya.ecommerce.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.divya.ecommerce.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repo;
	private final ProductMapper mapper;	

	public Integer createProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		var product = mapper.toProduct(request);
		return repo.save(product).getId();
	}

	public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
		// TODO Auto-generated method stub
		var productIds = request
						 .stream()
						 .map(ProductPurchaseRequest::productId)
						 .toList();
		var storedProducts = repo.findAllByIdInOrderById(productIds);
		
		if(productIds.size()!=storedProducts.size())
		{
			throw new ProductPurchaseException("One or more products doesn't exist/s");
		}
		
		var storedrequests = request
							 .stream()
							 .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
							 .collect(Collectors.toList());
		var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
		for(int i=0;i<productIds.size();i++)
		{
			var req = storedrequests.get(i);
			var db = storedProducts.get(i);
			
			if(db.getAvailableQuantity()< req.quantity())
			{
				throw new ProductPurchaseException("insufficient quantity for id"+ req.productId());
			}
			var availableQuantity = db.getAvailableQuantity()-req.quantity();
			db.setAvailableQuantity(availableQuantity);
			repo.save(db);
			purchasedProducts.add(mapper.toPurchasedProducts(db,req.quantity()));
		}
		return purchasedProducts;
	}

	public ProductResponse findById(Integer id) {
		// TODO Auto-generated method stub
		return repo.findById(id)
			   .map(mapper::fromProduct)
			   .orElseThrow(()->new EntityNotFoundException("product not present"+id));
		
		
	}

	public List<ProductResponse> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll()
			   .stream()
			   .map(mapper::fromProduct)
			   .collect(Collectors.toList());
	}	

}
