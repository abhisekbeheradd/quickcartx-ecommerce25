package com.jpa.demo.globalcart;

import java.util.ArrayList;
import java.util.List;

import com.jpa.demo.modelentity.Product;

public class GlobalData {
	public static List<Product> cart;

	static {
	   cart=new ArrayList<Product>();
		
	}

}
