package com.example.microservicios.cursos.models.ControllerCursos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons.examenes.models.entity.Examen;
import com.example.microservicios.cursos.models.ServiceCursos.CursosServices;
import com.example.microservicios.cursos.models.entity.CursoAlumno;
import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gcontroller.GController;

@RestController
@RequestMapping("/api/curso")
public class CursoController extends GController<Cursos, CursosServices> {
	
	
	

	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@DeleteMapping("/eliminaralumnocurso/{id}")
	public ResponseEntity<?>eliminarCursoAlumnoPorId(@PathVariable Long id){
		service.eliminarCursoAlumnoPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/generic/curso")
	@Override
    public ResponseEntity<?> getUsers() {
		List<Cursos> cursos = ((List<Cursos>) service.findAll()).stream().map(c ->{
			c.getCursosAlumnos().forEach(ca ->{
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno);
			});
			return c;
		}).collect(Collectors.toList());
       return ResponseEntity.ok().body(cursos);
   }
	
	@GetMapping("/get/{id}")
	@Override
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
       Optional<Cursos> a = service.findById(id);
       if (a.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       Cursos curso = a.get();
       if(curso.getCursosAlumnos().isEmpty()==false) {
    	   List<Long>ids=curso.getCursosAlumnos().stream().map(ca-> ca.getAlumnoId()).collect(Collectors.toList());
    	   List<Alumno>alumnos=(List<Alumno>) service.obtenerAlumnosPorCursos(ids);
    	   
    	   curso.setAlumnos(alumnos);
    	   
       }
       return ResponseEntity.ok().body(curso);
   }
	
	@GetMapping("/pagina")
	@Override
	   public ResponseEntity<?> getUsersPorPaginas(Pageable pageable) {
		  Page<Cursos>cursos= service.findAll(pageable).map(curso ->{
			  curso.getCursosAlumnos().forEach(ca->{
				  Alumno alumno = new Alumno();
				  alumno.setId(ca.getAlumnoId());
				  curso.addAlumno(alumno);
			  });
			  return curso;
		  } );
	      return ResponseEntity.ok().body(cursos);
	  }
	
	

	@GetMapping("/balanceador")
	public ResponseEntity<?> balanceador() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());

		return ResponseEntity.ok(response);
	}

	@PutMapping("/get/{id}")
	public ResponseEntity<?> editar(@RequestBody Cursos curso, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		dbCurso.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));

	}

	@PutMapping("/{id}/asignaralumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		alumnos.forEach(alumno -> {
			CursoAlumno cursoalumno= new CursoAlumno();
			cursoalumno.setAlumnoId(alumno.getId());
			cursoalumno.setCurso(dbCurso);
			dbCurso.addCursosAlumno(cursoalumno);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@PutMapping("/{id}/eliminaralumnos")
	public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		CursoAlumno cursoalumno= new CursoAlumno();
		cursoalumno.setAlumnoId(alumno.getId());
		dbCurso.removeCursosAlumno(cursoalumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarAlumnoId(@PathVariable Long id) {
		Cursos curso = service.findCursoByAlumnoId(id);
		if(curso!=null) {
			List<Long>examendesIds=(List<Long>) service.obtenerExamenesIdsConRespuestas(id);
			List<Examen>examenes = curso.getExamenes().stream().map(examen ->{
				if(examendesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			} ).collect(Collectors.toList());
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}

	@PutMapping("/{id}/asignarexamenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		examenes.forEach(examen -> {
			dbCurso.addExamen(examen);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

	@PutMapping("/{id}/eliminarexamen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		dbCurso.removeExamenes(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}

}
