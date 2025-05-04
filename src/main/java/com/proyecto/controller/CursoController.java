package com.proyecto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.entidades.Alumno;
import com.proyecto.entidades.Curso;
import com.proyecto.excepciones.CursoNoEncontradoException;
import com.proyecto.services.CursoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoServices cursoService;

    @GetMapping("/listar")
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/id/{id}")
    public Curso obtenerCursoPorId(@PathVariable Integer id) {
        return cursoService.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public Curso registrarCurso(@RequestBody Curso bean) {
       return cursoService.registrarCurso(bean);
    }


    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminarCurso(@PathVariable Integer id) {
        try {
            cursoService.eliminarPorId(id);
            return Map.of("mensaje", "Curso eliminado correctamente.");
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    
    @PutMapping("/actualizar/{id}")
    public Curso actualizarCurso(@PathVariable Integer id, @RequestBody Curso bean) {
        try {
            Curso cursoActualizado = cursoService.actualizarCurso(id, bean);
            return cursoActualizado;
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
	

    @ExceptionHandler(CursoNoEncontradoException.class)
    public ResponseEntity<String> manejarCursoNoEncontrado(CursoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
