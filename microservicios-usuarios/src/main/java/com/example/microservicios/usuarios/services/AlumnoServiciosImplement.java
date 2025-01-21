package com.example.microservicios.usuarios.services;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gservices.ServiciosImple;
import com.example.microservicios.usuarios.models.repositorio.AlumnoReposotorio;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class AlumnoServiciosImplement extends ServiciosImple<Alumno,AlumnoReposotorio> implements AlumnoService{

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findByNombreOrApellido(String nombre) {
		return repositoryGe.findByNombreOrApellido(nombre);
	}

   
}
