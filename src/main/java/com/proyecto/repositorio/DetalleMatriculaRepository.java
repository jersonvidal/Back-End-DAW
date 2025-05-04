package com.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidades.DetalleMatricula;

public interface DetalleMatriculaRepository extends JpaRepository<DetalleMatricula, Integer>{

    List<DetalleMatricula> findByMatricula_IdMatricula(int idMatricula);

}
