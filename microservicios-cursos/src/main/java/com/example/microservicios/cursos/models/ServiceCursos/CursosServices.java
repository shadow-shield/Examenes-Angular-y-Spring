package com.example.microservicios.cursos.models.ServiceCursos;


import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface CursosServices extends ServiceGenericos<Cursos> {
	
	public Cursos findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestas(@PathVariable Long alumnoId);

}
