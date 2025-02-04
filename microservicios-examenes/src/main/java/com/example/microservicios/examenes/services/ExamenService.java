package com.example.microservicios.examenes.services;

import java.util.List;

import com.example.commons.examenes.models.entity.Asignatura;
import com.example.commons.examenes.models.entity.Examen;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface ExamenService extends ServiceGenericos<Examen> {
	
	public List<Examen>findByNombre(String term);
	
	public Iterable<Asignatura> finnAllAsignatura();
	
	public Iterable<Long>findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
	
	

}
