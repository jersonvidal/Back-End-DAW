package com.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.Alumno;
import com.proyecto.entidades.GrupoCurso;
import com.proyecto.entidades.HorarioGrupo;
import com.proyecto.entidades.Matricula;
import com.proyecto.repositorio.AlumnoRepository;
import com.proyecto.repositorio.MatriculaRepository;

@Service
public class MatriculaService {
	
    @Autowired
    private MatriculaRepository repo;
    @Autowired
    private AlumnoRepository repo2;
    

    // Listar todas las matrículas
    public List<Matricula> listar() {
        return repo.findAll();
    }

    // Registrar matrícula
	 public void registrar(Matricula bean) {
		  Integer idAlumno = bean.getAlumno().getIdAlumno();

	        // Buscar el objeto completo desde la BD
	        Alumno alumno = repo2.findById(idAlumno).orElse(null);

	        // Asignarlo al horario
	        bean.setAlumno(alumno);

	        // Guardar
	        repo.save(bean);	    
	  }
	 
	 
	 // Eliminar
	    public void eliminar(int id) {
	        repo.deleteById(id);
	    }
	    public Matricula obtenerPorId(Integer idMatricula) {
	        return repo.findById(idMatricula).orElse(null);
	    }
}
