package com.example.microservicios.usuarios.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface AlumnoService extends ServiceGenericos<Alumno> {
	 public List<Alumno> findByNombreOrApellido(String nombre);
	
	 public Iterable<Alumno>finAllById(Iterable<Long> ids);
	 
	 public void eliminarCursoAlumnoPorId(Long id);
	
	
    
}
