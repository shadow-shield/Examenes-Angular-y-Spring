package com.example.microservicios_respuestas.models.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservicios_respuestas.models.entity.Respuesta;
import com.example.microservicios_respuestas.models.service.RespuestaService;

@RestController
@RequestMapping("api/respuesta")
public class RespuestaController {

	@Autowired
	private RespuestaService service;

	@PostMapping("/crear/respuesta")
	public ResponseEntity<?> crearRespuesta(@RequestBody Iterable<Respuesta> respuestas) {
		Iterable<Respuesta> respuestasDb = service.saveAll(respuestas);

		return ResponseEntity.status(HttpStatus.CREATED).body(respuestasDb);
	}
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	public ResponseEntity<?> obtenerRespuuestasporAlumno(@PathVariable Long alumnoId,@PathVariable Long examenId) {
		Iterable<Respuesta>respuestas=service.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestas);
	}
	
	
	@GetMapping("/alumno/{alumnoId}/examenes")
	public ResponseEntity<?> obtenerExamenesIdsConRespuestas(@PathVariable Long alumnoId) {
		Iterable<Long>examendesIds=service.findExamenesIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(examendesIds);
	}
	
	

}
