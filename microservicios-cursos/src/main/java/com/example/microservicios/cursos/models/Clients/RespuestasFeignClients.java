package com.example.microservicios.cursos.models.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicios-respuestas",url = "http://localhost:9010")
public interface RespuestasFeignClients {
	
	@GetMapping("/alumno/{alumnoId}/respondidos")
	public Iterable<Long> respuestaExamen(@PathVariable Long alumnoId);
	
}
