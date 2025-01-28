package com.example.microservicios_respuestas.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microservicios_respuestas.models.entity.Respuesta;
import com.example.microservicios_respuestas.models.repositorio.RespuestaRepositorio;



@Service
public class RespuestaImplemen implements RespuestaService {
	
	@Autowired
	private RespuestaRepositorio respuesta;

	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> saveAll) {
		return respuesta.saveAll(saveAll);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
	
		return respuesta.findRespuestaByAlumnoByExamen(alumnoId, examenId);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		return respuesta.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}

}
