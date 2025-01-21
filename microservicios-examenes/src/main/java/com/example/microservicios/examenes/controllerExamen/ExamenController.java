package com.example.microservicios.examenes.controllerExamen;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservicios.examenes.models.entity.Examen;

import com.example.microservicios.examenes.services.ExamenService;
import com.example.microservicios.genericos.Gcontroller.GController;


import java.util.Optional;

@RestController
@RequestMapping("/api/examen")
public class ExamenController extends GController<Examen,ExamenService> {
	
	@PutMapping("updatexamen/{id}")
	public ResponseEntity<?> updateExamen(@PathVariable Long id, @RequestBody Examen examen) {
		Optional<Examen> o= service.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb=o.get();
		examenDb.setNombre(examen.getNombre());
		
		examenDb.getPreguntas()
		.stream()
		.filter(pdb->!examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta);
		
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	

}
