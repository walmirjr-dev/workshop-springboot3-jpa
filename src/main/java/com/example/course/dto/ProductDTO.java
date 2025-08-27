package com.example.course.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.course.entities.Category;
import com.example.course.entities.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private double price;
	private String imgUrl;

	private Set<Long> categoryIDs = new HashSet<>();

	public ProductDTO() {
	}

	public ProductDTO(Product entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();

		for (Category cat : entity.getCategories()) {
			this.categoryIDs.add(cat.getId());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Long> getCategoryIDs() {
		return categoryIDs;
	}

	public void setCategoryIDs(Set<Long> categoryIDs) {
		this.categoryIDs = categoryIDs;
	}

}
