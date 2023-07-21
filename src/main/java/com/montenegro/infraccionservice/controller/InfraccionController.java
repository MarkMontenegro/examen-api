package com.montenegro.infraccionservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.montenegro.infraccionservice.converter.InfraccionConverter;
import com.montenegro.infraccionservice.dto.InfraccionDTO;
import com.montenegro.infraccionservice.entity.Infracciones;
import com.montenegro.infraccionservice.service.InfraccionService;
import com.montenegro.infraccionservice.utils.WrapperResponse;


import com.montenegro.infraccionservice.converter.InfraccionConverter;


@RestController
@RequestMapping("/v1/articulos")
//se cambia a dto cuando se crea el articulo deteo

public class InfraccionController {
	@Autowired
	private InfraccionService service;
	@Autowired
	private InfraccionConverter converter;
	@GetMapping
	private ResponseEntity<List<InfraccionDTO>> findAll(
			@RequestParam(value="nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value="offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value="limit" , required = false, defaultValue = "5") int pageSize	
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Infracciones> articulos;
		if (nombre==null) {
			articulos=service.findAll(page);
		}else {
			articulos=service.findByDni(nombre,page);
		}
		/*
		if(articulos.isEmpty()) {
			return ResponseEntity.noContent().build();
		
		}*/
		List<InfraccionDTO> articulosDTO=converter.fromEntity(articulos);
		return new WrapperResponse(true,"success",articulosDTO).createResponse(HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<WrapperResponse<InfraccionDTO>> findById(@PathVariable("id") int id){
		Infracciones articulo = service.findById(id);
		/*if (articulo==null) {
			return ResponseEntity.notFound().build();
		}*/
		InfraccionDTO articuloDTO=converter.fromEntity(articulo);
		return new WrapperResponse<InfraccionDTO>(true,"success",articuloDTO).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InfraccionDTO> create(@RequestBody InfraccionDTO articuloDTO){
		Infracciones registro = service.save(converter.fromDTO(articuloDTO));
		InfraccionDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success", registroDTO).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<InfraccionDTO> update(@PathVariable("id") int id,@RequestBody InfraccionDTO articuloDTO){
		Infracciones registro = service.update(converter.fromDTO(articuloDTO));
		/*if (registro == null) {
			return ResponseEntity.notFound().build();
		}*/
		InfraccionDTO registroDTO = converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<InfraccionDTO> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
	}

}
