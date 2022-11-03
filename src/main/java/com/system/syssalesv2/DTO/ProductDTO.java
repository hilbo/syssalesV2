package com.system.syssalesv2.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.Product;

public class ProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Double price;
		
	private Set<Category> categories = new HashSet<>();
	
	public ProductDTO() {
	}
	
	public ProductDTO(Product product) {
		this.name = product.getName();
		this.price = product.getPrice();
		this.categories.addAll(product.getCategories());
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public List<CategoryDTO> getlistCat(){
		List<CategoryDTO> lcat = new ArrayList<>();
		for (Category category : categories) {
			CategoryDTO catDTO = new CategoryDTO(category);
			lcat.add(catDTO);
		}
		return lcat;
	}

}
