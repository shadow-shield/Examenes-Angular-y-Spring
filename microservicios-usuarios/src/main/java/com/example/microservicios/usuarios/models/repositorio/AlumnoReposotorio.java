package com.example.microservicios.usuarios.models.repositorio;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoReposotorio extends JpaRepository<Alumno, Long> {
    @Query("select a from Alumno a where upper(a.nombre) like upper(concat('%',?1,'%')) or upper(a.apellido) like upper(concat('%',?1,'%'))")
    public List<Alumno> findByNombreOrApellido(String nombre);
    
    
    public Iterable<Alumno>findAllByOrderByIdAsc();
    
    public Page<Alumno>findAllByOrderByIdAsc(Pageable pageable);

}
