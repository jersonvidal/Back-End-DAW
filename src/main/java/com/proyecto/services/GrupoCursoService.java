package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.Curso;
import com.proyecto.entidades.Docente;
import com.proyecto.entidades.GrupoCurso;
import com.proyecto.entidades.HorarioGrupo;
import com.proyecto.excepciones.GrupoCursoNoEncontradoException;
import com.proyecto.repositorio.CursoRepository;
import com.proyecto.repositorio.DocenteRepository;
import com.proyecto.repositorio.GrupoCursoRepository;

@Service
public class GrupoCursoService {

	@Autowired
	private GrupoCursoRepository repo;
	
	@Autowired
	private DocenteRepository repo2;
	
	@Autowired
	private CursoRepository repo3;

	// Listar todos
	public List<GrupoCurso> listar() {
		return repo.findAll();
	}

	// Registrar con horarios
	public void registrar(GrupoCurso grupo) {
		for (HorarioGrupo h : grupo.getHorarios()) {
			h.setGrupoCurso(grupo); // Muy importante
		}
		repo.save(grupo);
	}

	// Buscar por ID
	public GrupoCurso buscarPorId(Integer id) {
		Optional<GrupoCurso> grupo = repo.findById(id);
		if (grupo.isPresent()) {
			return grupo.get();
		} else {
			throw new GrupoCursoNoEncontradoException("GrupoCurso con ID " + id + " no encontrado.");
		}
	}

	 // Eliminar
    public void eliminar(int id) {
        repo.deleteById(id);
    }
 
    // Actualizar
    public void actualizarGrupoCurso(GrupoCurso bean) {
        // Buscar el GrupoCurso existente por ID
        Optional<GrupoCurso> grupoExistente = repo.findById(bean.getIdGrupo());
        if (grupoExistente.isPresent()) {
            GrupoCurso grupo = grupoExistente.get();

            // Actualizar Docente (si se cambió)
            if (bean.getDocente() != null && bean.getDocente().getIdDocente() != null) {
                Docente docente = repo2.findById(bean.getDocente().getIdDocente())
                        .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
                grupo.setDocente(docente);
            }

            // Actualizar Curso (si se cambió)
            if (bean.getCurso() != null && bean.getCurso().getIdCurso() != null) {
                Curso curso = repo3.findById(bean.getCurso().getIdCurso())
                        .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
                grupo.setCurso(curso);
            }

            // Actualizar campos restantes
            grupo.setSemestre(bean.getSemestre());
            grupo.setAnio(bean.getAnio());
            grupo.setTurno(bean.getTurno());
            grupo.setModalidad(bean.getModalidad());

            // Guardar cambios
            repo.save(grupo);
        } else {
            throw new RuntimeException("GrupoCurso no encontrado");
        }
    }
}
