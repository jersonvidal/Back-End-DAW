package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.Alumno;
import com.proyecto.entidades.Curso;
import com.proyecto.excepciones.AlumnoNoEncontradoException;
import com.proyecto.excepciones.CursoNoEncontradoException;
import com.proyecto.repositorio.CursoRepository;

@Service
public class CursoServices {

    @Autowired
    private CursoRepository repo;

    // Listar Cursos
    public List<Curso> listarCursos() {
        return repo.findAll();
    }

    // Insertar o actualizar Curso
    public Curso registrarCurso(Curso bean) {
       return repo.save(bean);
    }

    // Buscar Curso por ID
    public Curso buscarPorId(Integer id) {
        Optional<Curso> curso = repo.findById(id);
        if (curso.isPresent()) {
            return curso.get();
        } else {
            throw new CursoNoEncontradoException("Curso con ID: " + id + " no encontrado");
        }
    }

    // Eliminar Curso
    public void eliminarPorId(Integer id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("El curso con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }
    
	// Actualizar Alumno
	public Curso actualizarCurso(Integer id, Curso cursoActualizado) {
	    // Primero verificamos si el alumno existe
	    if (!repo.existsById(id)) {
	        throw new CursoNoEncontradoException("Alumno con ID: " + id + " no encontrado");
	    }

	    // Si el alumno existe, simplemente guardamos el alumno actualizado
	    cursoActualizado.setIdCurso(id); // Aseguramos que el id se mantenga igual
	    return repo.save(cursoActualizado); // El método save de JPA actualizará automáticamente
	}
}
