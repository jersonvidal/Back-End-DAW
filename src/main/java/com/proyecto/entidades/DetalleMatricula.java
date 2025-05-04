package com.proyecto.entidades;

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
@Table(name = "tb_detalle_matricula")
@Data
public class DetalleMatricula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle")
	private Integer idDetalle;

	@ManyToOne
	@JoinColumn(name = "id_matricula", nullable = false)
	private Matricula matricula;

	@ManyToOne
	@JoinColumn(name = "id_grupo", nullable = false)
	private GrupoCurso grupoCurso;

	@Column(name = "estado_detalle", nullable = false)
	private boolean estado; // Ej. activa, inactiva

}

