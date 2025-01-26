package com.example.microservicios.examenes.models.repositorioExamen;

import org.springframework.data.repository.CrudRepository;

import com.example.commons.examenes.models.entity.Asignatura;

public interface AsignaturaReposi extends CrudRepository<Asignatura, Long> {

}
