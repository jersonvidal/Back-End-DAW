package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.DetalleMatricula;
import com.proyecto.entidades.GrupoCurso;
import com.proyecto.entidades.Matricula;
import com.proyecto.repositorio.DetalleMatriculaRepository;
import com.proyecto.repositorio.GrupoCursoRepository;
import com.proyecto.repositorio.MatriculaRepository;

@Service
public class DetalleMatriculaService {
	 @Autowired
	    private DetalleMatriculaRepository repo;

	    @Autowired
	    private MatriculaRepository repoMatricula;

	    @Autowired
	    private GrupoCursoRepository repoGrupoCurso;

	    public void registrar(DetalleMatricula bean) {
	        Integer idMatricula = bean.getMatricula().getIdMatricula();
	        Integer idGrupo = bean.getGrupoCurso().getIdGrupo();

	        Matricula matricula = repoMatricula.findById(idMatricula).orElse(null);
	        GrupoCurso grupo = repoGrupoCurso.findById(idGrupo).orElse(null);

	        bean.setMatricula(matricula);
	        bean.setGrupoCurso(grupo);

	        repo.save(bean);
	    }

	    public List<DetalleMatricula> listar() {
	        return repo.findAll();
	    }

	    public List<DetalleMatricula> listarPorMatricula(int idMatricula) {
	        return repo.findByMatricula_IdMatricula(idMatricula);
	    }

	    public Optional<DetalleMatricula> buscarPorId(int idDetalle) {
	        return repo.findById(idDetalle);
	    }

	    public void eliminar(int idDetalle) {
	        repo.deleteById(idDetalle);
	    }

	    public void actualizar(DetalleMatricula bean) {
	        Integer idMatricula = bean.getMatricula().getIdMatricula();
	        Integer idGrupo = bean.getGrupoCurso().getIdGrupo();

	        Matricula matricula = repoMatricula.findById(idMatricula).orElse(null);
	        GrupoCurso grupo = repoGrupoCurso.findById(idGrupo).orElse(null);

	        bean.setMatricula(matricula);
	        bean.setGrupoCurso(grupo);

	        repo.save(bean);
	    }
}
