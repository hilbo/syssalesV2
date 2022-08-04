package com.system.syssalesv2.entities;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long name;
	
	public Category() {
	}

	public Category(Long id, Long name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
}
