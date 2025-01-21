package com.example.microservicios.examenes.models.repositorioExamen;

import com.example.microservicios.examenes.models.entity.Examen;
import org.springframework.data.repository.CrudRepository;

public interface ExamenRepositorio extends CrudRepository<Examen, Long> {

}
