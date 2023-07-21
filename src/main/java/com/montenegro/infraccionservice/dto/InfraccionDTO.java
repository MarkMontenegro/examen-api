package com.montenegro.infraccionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class InfraccionDTO {
	
	private int id;
	private String dni;
	private String falta;	
}
