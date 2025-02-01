package com.example.microservicios.usuarios.services;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.example.microservicios.genericos.Gservices.ServiciosImple;
import com.example.microservicios.usuarios.clients.CursoFeignClient;
import com.example.microservicios.usuarios.models.repositorio.AlumnoReposotorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlumnoServiciosImplement extends ServiciosImple<Alumno,AlumnoReposotorio> implements AlumnoService{

	
	@Autowired
	private CursoFeignClient clientCurso;
	
	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findByNombreOrApellido(String nombre) {
		return repositoryGe.findByNombreOrApellido(nombre);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<Alumno> finAllById(Iterable<Long> ids) {
		
		return repositoryGe.findAllById(ids);
	}

	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		clientCurso.eliminarCursoAlumnoPorId(id);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		super.delete(id);
		this.eliminarCursoAlumnoPorId(id);
	}
	
	
	

   
}
