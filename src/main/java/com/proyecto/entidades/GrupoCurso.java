package com.proyecto.entidades;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_grupo_curso")
@Data
public class GrupoCurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grupo")
	private Integer idGrupo;

	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;

	@ManyToOne
	@JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
	private Docente docente;
	
	@OneToMany(mappedBy = "grupoCurso", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<HorarioGrupo> horarios = new ArrayList<>();


	@Column(name = "semestre", nullable = false)
	private String semestre;

	@Column(name = "anio", nullable = false)
	private Integer anio;

	@Column(name = "turno", nullable = false)
	private String turno; // Ej. mañana, tarde, noche

	@Column(name = "modalidad", nullable = false)
	private String modalidad; // Ej. presencial, virtual
}
