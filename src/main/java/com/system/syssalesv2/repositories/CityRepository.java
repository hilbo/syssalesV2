package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
	public City findByName(String name);
	
}
