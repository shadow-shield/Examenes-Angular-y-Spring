package com.example.microservicios.cursos.models.ServiceCursos;

import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface CursosServices extends ServiceGenericos<Cursos> {
	public Cursos findCursoByAlumnoId(Long id);

}
