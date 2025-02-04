package com.example.microservicios_respuestas.models.service;

//import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.example.commons.examenes.models.entity.Examen;
//import com.example.commons.examenes.models.entity.Pregunta;
//import com.example.microservicios_respuestas.clients.ExamenFeigClient;
import com.example.microservicios_respuestas.models.entity.Respuesta;
import com.example.microservicios_respuestas.models.repositorio.RespuestaRepositorio;



@Service
public class RespuestaImplemen implements RespuestaService {
	
	@Autowired
	private RespuestaRepositorio respuestaRespo;
	
	//@Autowired
	//private ExamenFeigClient examenClient;

	@Override

	public Iterable<Respuesta> saveAll(Iterable<Respuesta> saveAll) {
		return respuestaRespo.saveAll(saveAll);
	}

	@Override

	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
		/*Examen examen= examenClient.obtenerExamenPorId(examenId);
		List<Pregunta> preguntas=examen.getPreguntas();
		List<Long>preguntasIds=preguntas.stream().map(p->p.getId()).collect(Collectors.toList());
		List<Respuesta> respuestas=(List<Respuesta>) respuestaRespo.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntasIds);
		respuestas=respuestas.stream().map(r->{
			preguntas.forEach(p->{
				if(p.getId()==r.getPreguntaId()) {
					r.setPregunta(p);
				}
			});
			return r;
		}).collect(Collectors.toList());*/
		List<Respuesta> respuestas=(List<Respuesta>) respuestaRespo.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return respuestas;
	}

	@Override

	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		/*List<Respuesta>respuestasAlumno=(List<Respuesta>) respuestaRespo.findByAlumnoId(alumnoId);
		List<Long>examenIds=Collections.emptyList();
		if(respuestasAlumno.size()>0) {
			List<Long>preguntaIds= respuestasAlumno.stream().map(r ->r.getPreguntaId()).collect(Collectors.toList());
			examenIds=examenClient.obtenerExamenesIdsPorPorPreguntasIdsRespondidas(preguntaIds);
			
		}*/
		List<Respuesta>respuestasAlumno=(List<Respuesta>) respuestaRespo.findExamenesIdsConRespuestasByAlumno(alumnoId);
		List<Long>examenIds=respuestasAlumno
				.stream()
				.map(r->r.getPregunta().getExamen().getId())
				.distinct()
				.collect(Collectors.toList());
		return examenIds;
	}

	@Override
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
		
		return respuestaRespo.findByAlumnoId(alumnoId);
	}

}
