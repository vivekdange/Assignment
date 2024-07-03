package com.interview.product.DTO;

import com.interview.product.entity.Product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

	@NotNull(message = "Product Name should not be blank")
	private String name;

	@NotNull(message = "Descrption Name should not be blank")
	private String description;

	@NotNull(message = "Price is mandatory")
	@Min(value = 0, message = "Price should not be less than 0")
	private Double price;

	@Min(value = 0, message = "Price Should not less than 0")
	private int quantity;

	public Product toProduct(ProductDTO productDTO) {

		return Product.builder().description(description).name(name).price(price).quantity(quantity).build();

	}

}
