package com.interview.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.product.DTO.ProductDTO;
import com.interview.product.Exception.ResourceNotFoundException;
import com.interview.product.entity.Product;
import com.interview.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}


	@Operation(summary = "Create a new Product")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Product created", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }) })
	@PostMapping
	public Product addProduct(@Valid @RequestBody ProductDTO productDto) {

		return productService.addProduct(productDto.toProduct(productDto));

	}

	@Operation(summary = "Get all Product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found Products", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }) })
	@GetMapping
	public List<Product> getProducts() {

		return productService.getProducts();
	}
	
	@Operation(summary = "Find product by using product id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(
			@Parameter(description = "ID of product to be find") @PathVariable int id) {

		Optional<Product> product = productService.getProduct(id);

		if (product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(product.get());
		} else {
			throw new ResourceNotFoundException("Product not found for Given provided id");
		}

	}

	@Operation(summary = "Update existing product by using product id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Product id updated", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(
			@Parameter(description = "ID of product to be updated") @PathVariable int id,
			@Valid @RequestBody ProductDTO productDto) {

		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productDto.toProduct(productDto)));

	}

	@Operation(summary = "Delete product by using product id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Product deleted", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(
			@Parameter(description = "ID of product to be deleted") @PathVariable int id) {
		
		productService.deleteItem(id);
		return ResponseEntity.noContent().build();
	}
}
