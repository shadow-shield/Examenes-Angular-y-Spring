package com.example.microservicios.cursos.models.entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.commons.examenes.models.entity.Examen;
import com.example.microservicios.genericAlumnos.models.entity.Alumno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

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
    
    @JsonIgnoreProperties(value= {"curso"},allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "curso",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CursoAlumno> cursosAlumnos;

	//@OneToMany(fetch = FetchType.LAZY)
    @Transient
	private List<Alumno> alumnos;

	@ManyToMany(fetch = FetchType.LAZY)
    private List<Examen>examenes;

	@PrePersist
	public void prePersist(){
		this.createAt = new Date();
	}
	
	public Cursos() {
		this.alumnos=new ArrayList<>();
		this.examenes=new ArrayList<>();
		this.cursosAlumnos=new ArrayList<>();
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

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	public void addExamen(Examen examene) {
		this.examenes.add(examene);
	}
	
	public void removeExamenes(Examen examene) {
		this.examenes.remove(examene);
	}

	public List<CursoAlumno> getCursosAlumnos() {
		return cursosAlumnos;
	}

	public void setCursosAlumnos(List<CursoAlumno> cursosAlumnos) {
		this.cursosAlumnos = cursosAlumnos;
	}
	
	public void addCursosAlumno(CursoAlumno cursosAlumnos) {
		this.cursosAlumnos.add(cursosAlumnos);
	}
	
	public void removeCursosAlumno(CursoAlumno cursosAlumnos) {
		this.cursosAlumnos.remove(cursosAlumnos);
	}
	
	
	
	

}
