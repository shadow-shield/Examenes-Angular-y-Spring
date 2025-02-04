package com.example.microservicios_respuestas.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.commons.examenes.models.entity.Examen;

@FeignClient(name = "MICROSERVICIOS-EXAMENES", path = "/api/examen")
public interface ExamenFeigClient {
	  @GetMapping("/{id}")
	  public Examen obtenerExamenPorId(@PathVariable Long id);
	  
	  
	  @GetMapping("/respondidosporpreguntas")
	  public List<Long>obtenerExamenesIdsPorPorPreguntasIdsRespondidas(@RequestParam List<Long> preguntaIds);
}
