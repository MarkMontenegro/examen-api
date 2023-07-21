package com.montenegro.infraccionservice.converter;


import org.springframework.stereotype.Component;
import com.montenegro.infraccionservice.dto.InfraccionDTO;
import com.montenegro.infraccionservice.entity.Infracciones;


@Component
public class InfraccionConverter extends AbstractConverter<Infracciones, InfraccionDTO> {

	@Override
	public InfraccionDTO fromEntity(Infracciones entity) {
		
		if (entity == null) return null;
		return InfraccionDTO.builder()
			.id(entity.getId())
			.dni(entity.getDni())
			.falta(entity.getFalta())
			.build();
	}

	@Override
	public Infracciones fromDTO(InfraccionDTO dto) {
		if (dto == null) return null;
		return Infracciones.builder()
			.id(dto.getId())
			.dni(dto.getDni())
			.falta(dto.getFalta())
			.build();
	}

}
