package com.example.microservicios.usuarios.Controllers;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gcontroller.GController;
import com.example.microservicios.usuarios.services.AlumnoService;

import jakarta.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoController extends GController<Alumno, AlumnoService> {
	
	
	@GetMapping("/alumnoporcursos")
	public ResponseEntity<?> obtenerAlumnosPorCursos(@RequestParam List<Long> ids) {
		return ResponseEntity.ok(service.finAllById(ids));
	}
	

	@GetMapping("/editarfoto/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		
		Optional<Alumno> o = service.findById(id);

		if (o.isEmpty() || o.get().getFoto()==null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAlumno(@Valid @RequestBody Alumno alumno, @PathVariable Long id,
			BindingResult result) {

		if (result.hasErrors()) {
			return this.validar(result);

		}
		
		Optional<Alumno> alumno1 = service.findById(id);

		if (alumno1.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Alumno alumDb = alumno1.get();
		alumDb.setNombre(alumno.getNombre());
		alumDb.setApellido(alumno.getApellido());
		alumDb.setEmail(alumno.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumDb));
	}

	@GetMapping("/fltraralumno/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombreOrApellido(term));

	}

	@PostMapping("/crear/foto")
	public ResponseEntity<?> createFoto(@Valid Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo)
			throws IOException {
		if (!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.createUser(alumno, result);
	}

	@PutMapping("/updateFoto/{id}")
	public ResponseEntity<?> updateFoto(@Valid Alumno alumno, @PathVariable Long id, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) {
			return this.validar(result);

		}
		Optional<Alumno> alumno1 = service.findById(id);

		if (alumno1.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Alumno alumDb = alumno1.get();
		alumDb.setNombre(alumno.getNombre());
		alumDb.setApellido(alumno.getApellido());
		alumDb.setEmail(alumno.getEmail());

		if (!archivo.isEmpty()) {
			alumDb.setFoto(archivo.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumDb));
	}

}
