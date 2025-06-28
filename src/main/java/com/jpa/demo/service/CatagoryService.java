package com.jpa.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.demo.modelentity.Category;
import com.jpa.demo.repository.CatagoryRepository;

@Service
public class CatagoryService {
	@Autowired
	 private CatagoryRepository catagoryRepository;
	
	
	public List<Category> getAllCatagory() 
	{
		List<Category> list=catagoryRepository.findAll();
		return list;
	}
	public void addCatagory(Category category) {
		catagoryRepository.save(category);
	}
	
	public void removeCatagoryById(int id) {
		catagoryRepository.deleteById(id);
		
	}
	
	public Optional<Category> upadteCatagory(int id) {
		 return catagoryRepository.findById(id);
		
	}

}
