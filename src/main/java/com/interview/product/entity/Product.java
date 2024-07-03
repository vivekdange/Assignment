package com.interview.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@ GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private double price;
	private int quantity;
}
