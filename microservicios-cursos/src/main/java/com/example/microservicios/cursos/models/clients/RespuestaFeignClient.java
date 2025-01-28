package com.example.microservicios.cursos.models.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MICROSERVICIOS-RESPUESTAS", path = "/api/respuesta")
public interface RespuestaFeignClient {
	@GetMapping("/alumno/{alumnoId}/examenes")
	public Iterable<Long> obtenerExamenesIdsConRespuestas(@PathVariable Long alumnoId);
}
