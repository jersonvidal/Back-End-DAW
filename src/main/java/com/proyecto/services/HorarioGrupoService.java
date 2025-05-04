package com.proyecto.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.GrupoCurso;
import com.proyecto.entidades.HorarioGrupo;
import com.proyecto.repositorio.GrupoCursoRepository;
import com.proyecto.repositorio.HorarioGrupoRepository;

@Service
public class HorarioGrupoService {

	@Autowired
	HorarioGrupoRepository repo;
	@Autowired
	GrupoCursoRepository repo2;
	 public void registrar(HorarioGrupo bean) {
		  Integer idGrupo = bean.getGrupoCurso().getIdGrupo();

	        // Buscar el objeto completo desde la BD
	        GrupoCurso grupo = repo2.findById(idGrupo).orElse(null);

	        // Asignarlo al horario
	        bean.setGrupoCurso(grupo);

	        // Guardar
	        repo.save(bean);	    }
	 
	 
	 public List<HorarioGrupo> listar() {
	        return repo.findAll();
	    }
	 
	// Leer por ID
	    public Optional<HorarioGrupo> buscarPorId(int id) {
	        return repo.findById(id);
	    }

	    // Actualizar
	    public void actualizar(HorarioGrupo bean) {
	        // Se asume que el ID ya existe, y se quiere actualizar
	        Integer idGrupo = bean.getGrupoCurso().getIdGrupo();
	        GrupoCurso grupo = repo2.findById(idGrupo).orElse(null);
	        bean.setGrupoCurso(grupo);
	        repo.save(bean); // save() sirve tanto para insertar como para actualizar
	    }

	    // Eliminar
	    public void eliminar(int id) {
	        repo.deleteById(id);
	    }
	    public List<HorarioGrupo> buscarPorGrupo(int idGrupo) {
	        return repo.findByGrupoCurso_IdGrupo(idGrupo);
	    }
}
