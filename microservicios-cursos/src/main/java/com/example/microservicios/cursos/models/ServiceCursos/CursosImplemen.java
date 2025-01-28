package com.example.microservicios.cursos.models.ServiceCursos;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microservicios.cursos.models.clients.RespuestaFeignClient;
import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.cursos.models.repositorioCurso.CursoRepositorio;
import com.example.microservicios.genericos.Gservices.ServiciosImple;

@Service
public class CursosImplemen extends ServiciosImple<Cursos, CursoRepositorio> implements CursosServices {

	@Autowired
	private RespuestaFeignClient respuesta;
	
	@Override
	@Transactional(readOnly=true)
	public Cursos findCursoByAlumnoId(Long id) {
		return repositoryGe.findCursoByAlumnoId(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Long> obtenerExamenesIdsConRespuestas(Long alumnoId) {
		return respuesta.obtenerExamenesIdsConRespuestas(alumnoId);
	}

	

}
