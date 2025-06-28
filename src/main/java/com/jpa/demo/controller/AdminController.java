package com.jpa.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jpa.demo.service.CatagoryService;
import com.jpa.demo.service.ProductService;



import com.jpa.demo.dto.ProductDTO;
import com.jpa.demo.modelentity.Category;
import com.jpa.demo.modelentity.Product;



@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	CatagoryService catagoryService;
	@Autowired
	ProductService productService;
	
	
	@GetMapping("/admin")
	public String show() 
	{
		return "adminHome";
	}
	

@GetMapping("/admin/categories")
public String catagories(Model model) 
{
	model.addAttribute("categories", catagoryService.getAllCatagory());
	return "categories";

}
@GetMapping("admin/categories/add")
public String cartadd(Model model)

{
	model.addAttribute("category", new Category());
	return "categoriesAdd";
}

@PostMapping("admin/categories/add")
public String cartpost(@ModelAttribute("category") Category catagory)

{

	catagoryService.addCatagory(catagory);
	return "redirect:/admin/categories";
}

@GetMapping("/admin/categories/delete/{id}")
public String deleteCatagory(@PathVariable int id) {
	catagoryService.removeCatagoryById(id);
	return "redirect:/admin/categories";
	
}




@GetMapping("/admin/categories/update/{id}")
public String updateCatagory(@PathVariable int id,Model model) {
	Optional<Category> catagory = catagoryService.upadteCatagory(id);
	if(catagory.isPresent()) {
		model.addAttribute("category",catagory.get()); 
		return "categoriesAdd";
	}
	else {
		return "404";
	}
}
	
	//product secssion



	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String addProductGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", catagoryService.getAllCatagory());
		return "productsAdd";
	}
	
	
	
	///Done every things     
	
	
	
	
	
	
	
	
	
	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName) throws IOException{
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(catagoryService.upadteCatagory(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
	
		if(!file.isEmpty()) {
			imageUUID= file.getOriginalFilename();
			Path fileNameAndPath=Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}
		else {
			imageUUID=imgName;
		
		}
		product.setImageName(imageUUID);
		productService.addProducts(product);
		return "redirect:/admin/products";
		
		//done 

	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
	  productService.removeProduct(id);
	return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	
	public String updateProductGet(@PathVariable long id, Model model) {
		
		Product product = productService.getPoductById(id).get();
		ProductDTO productDTO =  new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		
		model.addAttribute("categories",catagoryService.getAllCatagory());
		model.addAttribute("productDTO",productDTO);
		
		
		return "productsAdd";
		
		
	}
		

}