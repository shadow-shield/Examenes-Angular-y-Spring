package com.example.microservicios.cursos.models.ServiceCursos;





import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface CursosServices extends ServiceGenericos<Cursos> {
	
	public Cursos findCursoByAlumnoId(Long id);
	
	public Iterable<Long> obtenerExamenesIdsConRespuestas( Long alumnoId);
	
	public Iterable<Alumno> obtenerAlumnosPorCursos(Iterable<Long> ids);
	
	public void eliminarCursoAlumnoPorId(Long id);

}
