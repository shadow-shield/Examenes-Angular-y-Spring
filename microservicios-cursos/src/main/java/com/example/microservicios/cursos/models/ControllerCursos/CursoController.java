package com.example.microservicios.cursos.models.ControllerCursos;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservicios.cursos.models.ServiceCursos.CursosServices;
import com.example.microservicios.cursos.models.entity.Cursos;
import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gcontroller.GController;

@RestController
@RequestMapping("/api/curso")
public class CursoController extends GController<Cursos, CursosServices> {

    @PutMapping("/api/cursos/{id}")
    public ResponseEntity<?> editar(@RequestBody Cursos curso, @PathVariable Long id) {
        Optional<Cursos> cursoDb = this.service.findById(id);
        if (!cursoDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cursos dbCurso = cursoDb.get();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));


    }

    @PutMapping("/api/cursos/{id}/asignaralumnos")
    public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id) {
        Optional<Cursos> cursoDb = this.service.findById(id);
        if (!cursoDb.isPresent()) {
            return ResponseEntity.notFound().build();
        }
		Cursos dbCurso = cursoDb.get();
		alumnos.forEach(alumno -> {
			dbCurso.addAlumno(alumno);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }
    
	@PutMapping("/api/cursos/{id}/eliminaralumnos")
	public ResponseEntity<?> eliminarAlumnos(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Cursos> cursoDb = this.service.findById(id);
		if (!cursoDb.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cursos dbCurso = cursoDb.get();
		dbCurso.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@GetMapping("api/cursos/alumno/{id}")
	public ResponseEntity<?>buscarAlumnoId(@PathVariable Long id){
		Cursos curso= service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}

}
