package com.example.microservicios.examenes.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.commons.examenes.models.entity.Asignatura;
import com.example.commons.examenes.models.entity.Examen;
import com.example.microservicios.examenes.models.repositorioExamen.AsignaturaReposi;
import com.example.microservicios.examenes.models.repositorioExamen.ExamenRepositorio;
import com.example.microservicios.genericos.Gservices.ServiciosImple;


@Service
public class ExamenImplemen extends ServiciosImple<Examen,ExamenRepositorio> implements ExamenService {


	@Autowired
	private AsignaturaReposi reposiasi;
	
	@Override
	@Transactional(readOnly=true)
	public List<Examen> findByNombre(String term) {
		return repositoryGe.findByNombre(term);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Asignatura> finnAllAsignatura() {
		return reposiasi.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds) {
		return repositoryGe.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds);
	}


	

}
