package com.example.microservicios.cursos.models.ControllerCursos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons.examenes.models.entity.Examen;
import com.example.microservicios.cursos.models.ServiceCursos.CursosServices;
import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gcontroller.GController;

@RestController
@RequestMapping("/api/curso")
public class CursoController extends GController<Cursos, CursosServices> {
	
	@Value("${config.balanceador.test}")
	private String balanceadorTest;
	
	@GetMapping("/balanceador")
	public ResponseEntity<?> balanceador() {
		Map<String, Object> response= new HashMap<String, Object>();
		response.put("balanceador",balanceadorTest);
		response.put("cursos",service.findAll());
	
		return ResponseEntity.ok(response);
	}
	

    @PutMapping("/api/cursos/{id}")
    public ResponseEntity<?> editar(@RequestBody Cursos curso, @PathVariable Long id) {
        Optional<Cursos> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cursos dbCurso = o.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));


    }

    @PutMapping("/api/cursos/{id}/asignaralumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
        Optional<Cursos> o = this.service.findById(id);
        if (!o.isPresent()) {
            return ResponseEntity.notFound().build();
        }
		Cursos dbCurso = o.get();
		alumnos.forEach(alumno -> {
			dbCurso.addAlumno(alumno);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    
	@PutMapping("/api/cursos/{id}/eliminaralumnos")
	public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Cursos> o = this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = o.get();
		dbCurso.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@GetMapping("/api/cursos/alumno/{id}")
	public ResponseEntity<?>buscarAlumnoId(@PathVariable Long id){
		Cursos curso= service.findCursoByAlumnoId(id);
		
		if(curso!=null) {
			List<Long> examendesIds=(List<Long>) service.respuestaExamen(id);
			List<Examen>examenes=curso.getExamenes().stream().map(examen->{
				if(examendesIds.contains(examen.getId())) {
					examen.setRespondido(true);
				}
				return examen;
			}).collect(Collectors.toList());
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/api/cursos/{id}/asignarexamenes")
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
	
	@PutMapping("/api/cursos/{id}/eliminarexamen")
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
