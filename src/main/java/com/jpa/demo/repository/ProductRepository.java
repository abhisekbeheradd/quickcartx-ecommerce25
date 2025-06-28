package com.jpa.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.demo.modelentity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategoryId(int id);

		



	

}
