package com.jpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jpa.demo.globalcart.GlobalData;
import com.jpa.demo.service.CatagoryService;
import com.jpa.demo.service.ProductService;

@Controller
public class HomeComtroller {
	@Autowired
	CatagoryService catagoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		return "index";
		
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		model.addAttribute("categories",catagoryService.getAllCatagory());
		model.addAttribute("products",productService.getAllProducts());
		
		return "shop";
	}
	
	//separate catagory or shop category
	@GetMapping("/shop/category/{id}")
	public String shopByCatagoy(Model model,@PathVariable int id) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		model.addAttribute("categories",catagoryService.getAllCatagory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		
		return "shop";
	}
	
	//show particular product details
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewParticualProductDetails(Model model,@PathVariable int id) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		
		model.addAttribute("product",productService.getPoductById(id).get());

		
		return "viewProduct";
	}
	
	

}
