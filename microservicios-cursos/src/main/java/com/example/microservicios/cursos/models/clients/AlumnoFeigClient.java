package com.example.microservicios.cursos.models.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;

@FeignClient(name = "MICROSERVICIOS-USUARIOS", path = "/api/alumno")
public interface AlumnoFeigClient {
	
	@GetMapping("/alumnoporcursos")
	public Iterable<Alumno> obtenerAlumnosPorCursos(@RequestParam Iterable<Long> ids);
	

}
