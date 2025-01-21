package com.example.microservicios.cursos.models.repositorioCurso;

import com.example.microservicios.cursos.models.entity.Cursos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepositorio extends CrudRepository<Cursos,Long> {
	@Query("select c from Cursos c join fetch c.alumnos a where a.id=?1")
	public Cursos findCursoByAlumnoId(Long id);

}
