package com.example.microservicios.examenes.controllerExamen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.commons.examenes.models.entity.Examen;
import com.example.commons.examenes.models.entity.Pregunta;
import com.example.microservicios.examenes.services.ExamenService;
import com.example.microservicios.genericos.Gcontroller.GController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/examen")
public class ExamenController extends GController<Examen, ExamenService> {

	@GetMapping("/respondidosporpreguntas")
	public ResponseEntity<?> obtenerExamenesIdsPorPorPreguntasIdsRespondidas(@RequestParam List<Long> preguntaIds) {

		return ResponseEntity.ok().body(service.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateExamen(@Valid @RequestBody Examen examen, BindingResult result,
			@PathVariable Long id) {

		if (result.hasErrors()) {
			return this.validar(result);

		}

		Optional<Examen> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());

		List<Pregunta> eliminadas= examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.collect(Collectors.toList());
		
		eliminadas.forEach(examenDb::removePregunta);

		examenDb.setPreguntas(examen.getPreguntas());
		examenDb.setAsignaturahijo(examen.getAsignaturahijo());
		examenDb.setAsignaturapadre(examen.getAsignaturapadre());

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}

	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrarExamen(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombre(term));

	}

	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas() {
		return ResponseEntity.ok(service.finnAllAsignatura());

	}

}
