package com.jpa.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jpa.demo.globalcart.GlobalData;
import com.jpa.demo.modelentity.Product;
import com.jpa.demo.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
	
		GlobalData.cart.add(productService.getPoductById(id).get());
		return "redirect:/";
	 
		
	}
	@GetMapping("/cart") 
	public String cartGet(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		return "cart";
	}
	
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		
		return "checkout";
		
	}
	
	

}
