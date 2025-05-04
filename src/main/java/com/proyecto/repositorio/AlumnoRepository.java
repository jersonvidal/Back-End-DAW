package com.proyecto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidades.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	Optional<Alumno> findByDni(String dni);

}
