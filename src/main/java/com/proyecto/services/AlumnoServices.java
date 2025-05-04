package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.Alumno;
import com.proyecto.excepciones.AlumnoNoEncontradoException;
import com.proyecto.repositorio.AlumnoRepository;

@Service
public class AlumnoServices {

	@Autowired
	private AlumnoRepository repo;

	// Listar Alumnos
	public List<Alumno> listarAlumnos() {
		return repo.findAll();
	}

	// Insertar Alumno
	public Alumno registrarAlumno(Alumno bean) {
		return repo.save(bean);
	}

	// Buscar DNI
	public Alumno buscarPorDni(String dni) {
		Optional<Alumno> alumno = repo.findByDni(dni); // Busca por DNI
		if (alumno.isPresent()) {
			return alumno.get();
		} else {
			throw new AlumnoNoEncontradoException("No se encontro alumno con DNI: " + dni); // Lanza excepción si no lo
																							// encuentra
		}
	}

	// Buscar ID Alumno
	public Alumno buscarPorId(Integer id) {
		Optional<Alumno> alumno = repo.findById(id);
		if (alumno.isPresent()) {
			return alumno.get();
		} else {
			throw new AlumnoNoEncontradoException("Alumno con ID: " + id + " no encontrado");
		}
	}

	//Eliminar alumno
	public void eliminarPorId(Integer id) {
	    if (!repo.existsById(id)) {
	        throw new RuntimeException("El alumno con ID " + id + " no existe.");
	    }
	    repo.deleteById(id);
	}
	// Actualizar Alumno
	public Alumno actualizarAlumno(Integer id, Alumno alumnoActualizado) {
	    // Primero verificamos si el alumno existe
	    if (!repo.existsById(id)) {
	        throw new AlumnoNoEncontradoException("Alumno con ID: " + id + " no encontrado");
	    }

	    // Si el alumno existe, simplemente guardamos el alumno actualizado
	    alumnoActualizado.setIdAlumno(id); // Aseguramos que el id se mantenga igual
	    return repo.save(alumnoActualizado); // El método save de JPA actualizará automáticamente
	}

	
}
