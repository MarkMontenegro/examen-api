package com.montenegro.infraccionservice.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.montenegro.infraccionservice.entity.Infracciones; @Repository

public interface InfraccionRepository extends JpaRepository<Infracciones, Integer>{
	List<Infracciones> findByDniContaining(String dni, Pageable page);
	Infracciones findByDni(String dni);
}
