package com.example.microservicios.examenes.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="preguntas")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

	@JsonIgnoreProperties(value = {"pregunta"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="examen_id")
	private Examen examen;


	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}

		if(!(obj instanceof Pregunta)) {
			return false;
		}
		Pregunta pre =(Pregunta) obj;

		return this.id != null && this.id.equals(pre.getId());
	}
}
