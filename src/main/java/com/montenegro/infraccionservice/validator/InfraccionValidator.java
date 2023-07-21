package com.montenegro.infraccionservice.validator;

import com.montenegro.infraccionservice.exceptions.ValidateServiceException;
import com.montenegro.infraccionservice.entity.Infracciones;

public class InfraccionValidator {
	public static void save(Infracciones infraccion) {
		if(infraccion.getDni()==null || infraccion.getDni().isEmpty()) {
			throw new ValidateServiceException("El Dni es requerido");
		}
		if (infraccion.getDni().length() > 8) {
			throw new ValidateServiceException("El Dni es muy largo");
		}
		if(infraccion.getDni()==null) {
			throw new ValidateServiceException("El Dni es requerido");
		}
		
	}
}
