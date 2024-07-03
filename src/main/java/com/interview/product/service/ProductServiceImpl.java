package com.interview.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.interview.product.Exception.ResourceNotFoundException;
import com.interview.product.entity.Product;
import com.interview.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product addProduct(Product product) {

		return productRepository.save(product);
	}

	@Override
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProduct(int id) {

		return productRepository.findById(id);
	}

	@Override
	public Product updateProduct(int id, Product product) {

		Optional<Product> productDetail = productRepository.findById(id);

		if (productDetail.isPresent()) {
			Product oldProduct = productDetail.get();

			oldProduct.setDescription(product.getDescription());
			oldProduct.setName(product.getName());
			oldProduct.setPrice(product.getPrice());
			oldProduct.setQuantity(product.getQuantity());
			
			return productRepository.save(oldProduct);
			
		} else {
			throw new ResourceNotFoundException("Update Failed, Product not Found for given id");
		}

	}

	@Override
	public void deleteItem(int id) {
		productRepository.deleteById(id);
	}

}
