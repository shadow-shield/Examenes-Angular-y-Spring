package com.example.microservicios.usuarios.services;

import java.util.List;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gservices.ServiceGenericos;

public interface AlumnoService extends ServiceGenericos<Alumno> {
	public List<Alumno> findByNombreOrApellido(String nombre);
    
}
