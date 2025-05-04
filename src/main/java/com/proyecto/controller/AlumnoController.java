package com.proyecto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.entidades.Alumno;
import com.proyecto.excepciones.AlumnoNoEncontradoException;
import com.proyecto.services.AlumnoServices;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/alumnos")
public class AlumnoController {

	@Autowired
	private AlumnoServices alumnoService;

	// Metodos GET
	@GetMapping("/listar")
	public List<Alumno> listarAlumnos() {
		return alumnoService.listarAlumnos();
	}

	@GetMapping("/dni/{dni}")
	public Alumno obtenerAlumnoPorDni(@PathVariable String dni) {
		return alumnoService.buscarPorDni(dni);
	}

	@GetMapping("/id/{id}")
	public Alumno obtenerAlumnoPorId(@PathVariable Integer id) {
		return alumnoService.buscarPorId(id);
	}

	@PostMapping("/registrar")
	public Alumno registrarAlumno(@RequestBody Alumno bean) {
	   return alumnoService.registrarAlumno(bean);
	}
	@PutMapping("/actualizar/{id}")
    public Alumno actualizarAlumno(@PathVariable Integer id, @RequestBody Alumno bean) {
        try {
            Alumno alumnoActualizado = alumnoService.actualizarAlumno(id, bean);
            return alumnoActualizado;
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
	
	 @DeleteMapping("/eliminar/{id}")
	    public Map<String, String> eliminarAlumno(@PathVariable Integer id) {
	        try {
	            alumnoService.eliminarPorId(id);
	            return Map.of("mensaje", "Alumno eliminado correctamente.");
	        } catch (RuntimeException ex) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
	        }
	    }

	// Excepciones
	@ExceptionHandler(AlumnoNoEncontradoException.class)
	public ResponseEntity<String> manejarAlumnoNoEncontradoException(AlumnoNoEncontradoException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // Respuesta 404 con el mensaje de la
																			// excepción
	}

}
