package com.carely.sistema_consultas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaConsultasApplication.class, args);
	}

}
