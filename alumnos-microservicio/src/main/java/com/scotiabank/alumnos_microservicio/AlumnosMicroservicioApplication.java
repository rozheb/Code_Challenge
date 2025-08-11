package com.scotiabank.alumnos_microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service","repository"})
public class AlumnosMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumnosMicroservicioApplication.class, args);
	}

}
