package com.divya.ecommerce.Category;

import java.util.List;

import com.divya.ecommerce.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Category {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
	private List<Product> products;
}
