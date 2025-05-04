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
import com.proyecto.excepciones.GrupoCursoNoEncontradoException;
import com.proyecto.services.GrupoCursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupocurso")
public class GrupoCursoController {

    @Autowired
    private GrupoCursoService service;

    // Listar
    @GetMapping("/listar")
    public List<GrupoCurso> listarGrupos() {
        return service.listar();
    }

    // Buscar por ID
    @GetMapping("/id/{id}")
    public GrupoCurso buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    // Registrar
    @PostMapping("/registrar")
    public void registrarGrupoCurso(@Valid @RequestBody GrupoCurso grupo) {
        service.registrar(grupo);
    }

    // Eliminar
    @DeleteMapping("/eliminar/{id}")
    public Map<String, String> eliminarGrupoCurso(@PathVariable int id) {
        try {
            service.eliminar(id);
            return Map.of("mensaje", "GrupoCurso eliminado correctamente.");
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Map<String, Object>> actualizarGrupoCurso(@PathVariable int id, @Valid @RequestBody GrupoCurso bean) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Asignar el ID recibido al bean
            bean.setIdGrupo(id);

            // Llamar al servicio para actualizar el GrupoCurso
            service.actualizarGrupoCurso(bean);

            response.put("mensaje", "GrupoCurso actualizado correctamente");
            response.put("codigo", HttpStatus.OK.value());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("mensaje", "Error al actualizar el GrupoCurso: " + e.getMessage());
            response.put("codigo", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    

    // Manejo de excepciones
    @ExceptionHandler(GrupoCursoNoEncontradoException.class)
    public ResponseEntity<String> manejarGrupoCursoNoEncontrado(GrupoCursoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
