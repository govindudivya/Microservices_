package com.divya.ecommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

	List<Product> findAllByIdInOrderById(List<Integer> productIds);

}
