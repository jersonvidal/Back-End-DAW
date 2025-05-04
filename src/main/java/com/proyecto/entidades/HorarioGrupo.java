package com.proyecto.entidades;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "tb_horario_grupo")
@Data
public class HorarioGrupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario")
	private Integer idHorario;

	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private GrupoCurso grupoCurso;

	@Column(name = "dia_semana", nullable = false)
	private String diaSemana; // Ej. lunes, martes, etc.

	@Column(name = "hora_inicio", nullable = false)
	private LocalTime  horaInicio;

	@Column(name = "hora_fin", nullable = false)
	private LocalTime  horaFin;

	@Column(name = "aula", nullable = false)
	private String aula;
}
