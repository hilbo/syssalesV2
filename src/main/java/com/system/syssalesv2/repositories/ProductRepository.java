package com.system.syssalesv2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.system.syssalesv2.entities.Category;
import com.system.syssalesv2.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p INNER JOIN p.categories c where p.name LIKE %:productName% AND c IN :categories")
	Page<Product> findByNameAndCategories(String productName, List<Category> categories, Pageable page);
}
