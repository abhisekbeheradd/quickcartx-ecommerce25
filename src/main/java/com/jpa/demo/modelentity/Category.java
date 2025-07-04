package com.jpa.demo.modelentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "category_id")
	private int id;
	@Column
	private String name;
	public Category() {
		super();
	}
	public Category(String name) {
		super();
		
		this.name = name;
	}
	public int getId() {

		return id;
	}
	public void setId(int id) {

		this.id = id;
	}
	public String getName() {

		return name;
	}
	public void setName(String name) {

		this.name = name;
	}

}
