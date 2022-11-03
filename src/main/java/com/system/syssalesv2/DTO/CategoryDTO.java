package com.system.syssalesv2.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.system.syssalesv2.entities.Category;

public class CategoryDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank
	private String name;
	
	public CategoryDTO() {
	}
	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
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
}
