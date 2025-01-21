package com.example.microservicios.cursos.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.microservicios.genericAlumnos.models.entity.Alumno;

@Entity
@Table(name="cursos")
public class Cursos {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;



	public void prePersist(){
		this.createAt = new Date();
	}
	
	public Cursos() {
		this.alumnos=new ArrayList<>();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Alumno>getAlumnos(){
		return alumnos;
	}
	
	public void setAlumnos(List<Alumno> alumnos){
		this.alumnos = alumnos;
	}
	public void addAlumno(Alumno alumno){
		this.alumnos.add(alumno);
	}
	public void removeAlumno(Alumno alumno){
		this.alumnos.remove(alumno);
	}

}
