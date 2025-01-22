package com.example.microservicios.examenes.models.repositorioExamen;

import com.example.commons.examenes.models.entity.Examen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExamenRepositorio extends JpaRepository<Examen, Long> {
     @Query("select e from Examen e where e.nombre like %?1%")
     public List<Examen>findByNombre(String term);
}
