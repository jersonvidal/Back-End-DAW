package com.proyecto.entidades;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_matricula")
@Data
public class Matricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_matricula")
	private Integer idMatricula;

	@ManyToOne
	@JoinColumn(name = "id_alumno", nullable = false)
	private Alumno alumno;

	@Column(name = "fecha_matricula", nullable = false)
	private Date fechaMatricula;

}
