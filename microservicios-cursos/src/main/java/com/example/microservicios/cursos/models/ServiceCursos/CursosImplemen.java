package com.example.microservicios.cursos.models.ServiceCursos;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.cursos.models.repositorioCurso.CursoRepositorio;
import com.example.microservicios.genericos.Gservices.ServiciosImple;

@Service
public class CursosImplemen extends ServiciosImple<Cursos, CursoRepositorio> implements CursosServices {

	@Override
	@Transactional(readOnly=true)
	public Cursos findCursoByAlumnoId(Long id) {
		// TODO Auto-generated method stub
		return repositoryGe.findCursoByAlumnoId(id);
	}

	

}
