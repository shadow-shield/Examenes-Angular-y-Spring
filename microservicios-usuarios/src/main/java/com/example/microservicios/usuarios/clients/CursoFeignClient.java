package com.example.microservicios.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MICROSERVICIOS-CURSOS",path = "/api/curso")
public interface CursoFeignClient {
	
	@DeleteMapping("/eliminaralumnocurso/{id}")
	public void eliminarCursoAlumnoPorId(@PathVariable Long id);
}
