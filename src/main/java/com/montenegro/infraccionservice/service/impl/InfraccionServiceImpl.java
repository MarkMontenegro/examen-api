package com.montenegro.infraccionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montenegro.infraccionservice.entity.Infracciones;
import com.montenegro.infraccionservice.exceptions.GeneralServiceException;
import com.montenegro.infraccionservice.exceptions.NoDataFoundException;
import com.montenegro.infraccionservice.service.InfraccionService;
import com.montenegro.infraccionservice.validator.InfraccionValidator;
import com.montenegro.infraccionservice.exceptions.ValidateServiceException;
import lombok.extern.slf4j.Slf4j;

import com.montenegro.infraccionservice.repository.InfraccionRepository;


@Service
@Slf4j

public class InfraccionServiceImpl implements InfraccionService{
	@Autowired
	private InfraccionRepository repository;
	@Override
	@Transactional(readOnly = true)
	public List<Infracciones> findAll(Pageable page) {
	
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infracciones> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infracciones findById(int id) {
		try {
			Infracciones registro= repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Infracciones save(Infracciones infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			if (repository.findByDni(infraccion.getDni())!= null) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+infraccion.getDni());
			}
			infraccion.setActivo(true);
			Infracciones registro=repository.save(infraccion);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	@Transactional
	public Infracciones update(Infracciones infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			Infracciones registro= repository.findById(infraccion.getId()).orElseThrow(()->new NoDataFoundException("No se encontro un registro con esa ID"));
			Infracciones registroD = repository.findByDni(infraccion.getDni());
			if (registroD != null && registroD.getId() != infraccion.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el nombre"+infraccion.getDni());
			}
			registro.setDni(infraccion.getDni());
			registro.setFalta(infraccion.getFalta());
			repository.save(infraccion);
			return infraccion;
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
	}
	}
	
	@Override
	@Transactional
	public void delete(int id) {
	
		try {
			Infracciones infraccion=repository.findById(id).orElseThrow(()-> new NoDataFoundException("No existe el registro con ese ID."));
			repository.delete(infraccion);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
		
	}

}
