package com.example.microservicios.cursos.models.repositorioCurso;

import com.example.microservicios.cursos.models.entity.Cursos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CursoRepositorio extends JpaRepository<Cursos,Long> {
	@Query("select c from Cursos c join fetch c.cursosAlumnos a where a.alumnoId=?1")
	public Cursos findCursoByAlumnoId(Long id);
	
	
	@Modifying
	@Query("delete from CursoAlumno ca where ca.alumnoId=?1")
	public void eliminarCursoAlumnoPorId(Long id);

}
