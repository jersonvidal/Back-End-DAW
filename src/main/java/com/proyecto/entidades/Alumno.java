package com.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name ="tb_alumno")
@Data
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alumno")
	private Integer idAlumno;
	
	@Column(name = "nombres_alumno", nullable = false)
	@NotNull(message = "El nombre no puede ser nulo")
	@Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
	private String nombres;
	
	@Column(name = "apellidos_alumno", nullable = false)
	@NotNull(message = "El apellido no puede ser nulo")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
	private String apellidos;
	
    @Column(name = "dni_alumno", length = 8, nullable = false, unique = true)
    @NotNull(message = "El DNI no puede ser nulo")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres") //Manejo de excepcion min 8 - max 8 digitos en dni
    																				//Manejado en la clase ErroResponse - ControllerAdvice
	private String dni;
    
    @Column(name = "correo", nullable = false, unique = true)
    @NotNull(message = "El correo del alumno no puede ser nulo")
    private String correo;
    
   
}
