package com.interview.product.service;

import java.util.List;
import java.util.Optional;

import com.interview.product.entity.Product;

public interface ProductService {

	public Product addProduct(Product product);

	public List<Product> getProducts();

	public Optional<Product> getProduct(int id);

	public Product updateProduct(int id, Product product);

	public void deleteItem(int id);
}
