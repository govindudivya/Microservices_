package com.divya.ecommerce.product;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.divya.ecommerce.Category.Category;

import jakarta.validation.constraints.NotNull;

@Service
public class ProductMapper {

	public Product toProduct(ProductRequest request) {
		// TODO Auto-generated method stub
		
		
		return Product.builder()
			   .id(request.id())
			   .name(request.name())
			   .price(request.price())
			   .description(request.description())
			   .availableQuantity(request.availableQuantity())
			   .category(
					   Category.builder()
					   .id(request.category_id())
					   .build())
			   .build();
	}

	public ProductResponse fromProduct(Product product) {
		// TODO Auto-generated method stub
		
		return new ProductResponse(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getAvailableQuantity(),
				product.getPrice(),
				product.getCategory().getId(),
				product.getCategory().getName(),
				product.getCategory().getDescription()
				);				
	}

	public ProductPurchaseResponse toPurchasedProducts(Product db,
			 double quantity) {
		// TODO Auto-generated method stub
		
		return new ProductPurchaseResponse(
				db.getId(),
				db.getName(),
				db.getDescription(),
				db.getAvailableQuantity(),
				db.getPrice()
				
				);
	}

}
