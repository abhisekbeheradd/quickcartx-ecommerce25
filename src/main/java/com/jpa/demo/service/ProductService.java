package com.jpa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.demo.modelentity.Product;
import com.jpa.demo.repository.ProductRepository;

@Service

public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() 
	{
		List<Product> list =this.productRepository.findAll();
		return list;
	}
	
	public void addProducts(Product product) {
		productRepository.save(product);
	}
	public void removeProduct(long id) {
		productRepository.deleteById(id);
		
	}
	public Optional<Product> getPoductById(long id) {
		return productRepository.findById(id);
	}

	public List<Product> getAllProductsByCategoryId(int id) {
		return productRepository.findAllByCategoryId(id);
		
	}

	

}
