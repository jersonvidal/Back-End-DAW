package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.entidades.GrupoCurso;
import com.proyecto.entidades.Matricula;
import com.proyecto.services.AlumnoServices;
import com.proyecto.services.CursoServices;
import com.proyecto.services.DocenteServices;
import com.proyecto.services.MatriculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService service;

 
    // Listar
    @GetMapping("/listar")
    public List<Matricula> listarMatriculas() {
        return service.listar();
    }

    // Registrar
    @PostMapping("/registrar")
    public void registrarMatricula(@Valid @RequestBody Matricula bean) {
        service.registrar(bean);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminarMatricula(@PathVariable int id) {
        try {
            service.eliminar(id);
            return Map.of("mensaje", "Horario eliminado correctamente.");
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obtenerMatriculaPorId(@PathVariable("id") Integer idMatricula) {
        Matricula matricula = service.obtenerPorId(idMatricula);
        if (matricula == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matricula);
    }
    
}
