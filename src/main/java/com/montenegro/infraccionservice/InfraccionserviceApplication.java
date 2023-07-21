package com.montenegro.infraccionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@SpringBootApplication 
@EnableJpaRepositories
public class InfraccionserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraccionserviceApplication.class, args);
	}

}
