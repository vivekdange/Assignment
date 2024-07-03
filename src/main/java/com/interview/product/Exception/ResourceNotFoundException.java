package com.interview.product.Exception;

public class ResourceNotFoundException extends RuntimeException{

	public  ResourceNotFoundException(String msg) {
		 super( msg);
	}
}
