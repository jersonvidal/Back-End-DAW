package com.proyecto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.entidades.Alumno;
import com.proyecto.entidades.Docente;
import com.proyecto.excepciones.DocenteNoEncontradoException;
import com.proyecto.services.DocenteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteServices docenteService;

    @GetMapping("/listar")
    public List<Docente> listarDocentes() {
        return docenteService.listarDocentes();
    }

    @GetMapping("/id/{id}")
    public Docente obtenerDocentePorId(@PathVariable Integer id) {
        return docenteService.buscarPorId(id);
    }

    @PostMapping("/registrar")
    public void registrarDocente(@Valid @RequestBody Docente bean) {
        docenteService.registrarDocente(bean);
    }
    @PutMapping("/actualizar/{id}")
    public Docente actualizarDocente(@PathVariable Integer id, @RequestBody Docente bean) {
        try {
            Docente docenteActualizado = docenteService.actualizarDocente(id, bean);
            return docenteActualizado;
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminarAlumno(@PathVariable Integer id) {
        try {
            docenteService.eliminarPorId(id);
            return Map.of("mensaje", "Alumno eliminado correctamente.");
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @ExceptionHandler(DocenteNoEncontradoException.class)
    public ResponseEntity<String> manejarDocenteNoEncontrado(DocenteNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
