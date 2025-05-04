package com.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tb_curso")
@Data
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer idCurso;

	@Column(name = "codigo_curso", nullable = false, unique = true)
	@NotNull(message = "El código del curso no puede ser nulo")
	private String codigo;

	@Column(name = "nombre_curso", nullable = false)
	@NotNull(message = "El nombre del curso no puede ser nulo")
	private String nombre;

	@Column(name = "creditos_curso", nullable = false)
	@NotNull(message = "Los créditos no pueden ser nulos")
	private Integer creditos;
}
