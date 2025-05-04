package com.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidades.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

}
