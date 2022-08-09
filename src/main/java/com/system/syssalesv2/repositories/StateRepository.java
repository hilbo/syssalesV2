package com.system.syssalesv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.syssalesv2.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
