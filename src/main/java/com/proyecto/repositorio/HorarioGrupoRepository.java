package com.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidades.HorarioGrupo;

public interface HorarioGrupoRepository extends JpaRepository<HorarioGrupo, Integer> {
    List<HorarioGrupo> findByGrupoCurso_IdGrupo(int idGrupo);

}
