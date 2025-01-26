package com.example.microservicios.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.example.microservicios.cursos.models.Clients")
@SpringBootApplication
@EntityScan({"com.example.microservicios.genericAlumnos.models.entity",
             "com.example.microservicios.cursos.models.entity",
             "com.example.commons.examenes.models.entity"})
public class MicroserviciosCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursosApplication.class, args);
	}

}
