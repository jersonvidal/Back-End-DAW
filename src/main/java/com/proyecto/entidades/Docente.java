package com.proyecto.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tb_docente")
@Data
public class Docente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_docente")
	private Integer idDocente;

	@Column(name = "nombre_docente", nullable = false)
	@NotNull(message = "El nombre del docente no puede ser nulo")
	private String nombre;

	@Column(name = "apellido_docente", nullable = false)
	@NotNull(message = "El nombre del docente no puede ser nulo")
	private String apellido;

	@Column(name = "especialidad_docente", nullable = false)
	@NotNull(message = "La especialidad del docente no puede ser nula")
	private String especialidad;

	// Relación uno a muchos (un docente puede enseñar varios grupos de curso)
	@OneToMany(mappedBy = "docente") // 'docente' es el campo en GrupoCurso que lo referencia
	@JsonIgnore
	private List<GrupoCurso> gruposCurso;
}
