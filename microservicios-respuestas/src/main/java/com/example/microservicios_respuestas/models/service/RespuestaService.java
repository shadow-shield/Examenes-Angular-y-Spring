package com.example.microservicios_respuestas.models.service;

import com.example.microservicios_respuestas.models.entity.Respuesta;

public interface RespuestaService {

	public Iterable<Respuesta>saveAll(Iterable<Respuesta> respuesta);
	
	public Iterable<Respuesta>findRespuestaByAlumnoByExamen(Long alumnoId,Long examenId);
	
	public Iterable<Long>findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
