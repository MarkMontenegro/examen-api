package com.montenegro.infraccionservice.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.montenegro.infraccionservice.entity.Infracciones;

public interface InfraccionService {
	
	public List<Infracciones> findAll(Pageable page);
	public List<Infracciones> findByDni(String dni, Pageable page);
	public Infracciones findById(int id);
	public Infracciones save (Infracciones infraccion);
	public Infracciones update (Infracciones infraccion);
	public void delete (int id);

}
