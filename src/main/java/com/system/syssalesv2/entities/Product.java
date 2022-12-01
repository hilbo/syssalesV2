package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private Integer quantityStock;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "tb_product_category")
	@JoinColumn(name = "product_id")
	@JoinColumn(name = "category_id")
	private Set<Category> categories = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany
	private List<OrderItem> orderItens = new ArrayList<>();
	
	public Product() {
	}

	public Product(Long id, String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantityStock = 0;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	public List<OrderItem> getOrderItens() {
		return orderItens;
	}

	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void insertQuantityStock(Integer quantity) {
		this.quantityStock = this.quantityStock + quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
}
