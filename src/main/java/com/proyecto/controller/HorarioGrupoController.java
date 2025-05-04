package com.proyecto.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.proyecto.entidades.HorarioGrupo;
import com.proyecto.services.HorarioGrupoService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/horario")
public class HorarioGrupoController {
	 @Autowired
	    private HorarioGrupoService service;

	    // Listar todos
	    @GetMapping("/listar")
	    public List<HorarioGrupo> listarHorarioGrupos() {
	        return service.listar();
	    }

	    // Registrar
	    @PostMapping("/registrar")
	    public void registrarHorarioGrupo(@Valid @RequestBody HorarioGrupo bean) {
	        service.registrar(bean);
	    }
	    
	    // Buscar por ID
	    @GetMapping("/buscar/{id}")
	    public ResponseEntity<HorarioGrupo> buscarPorId(@PathVariable int id) {
	        Optional<HorarioGrupo> horario = service.buscarPorId(id);
	        if (horario.isPresent()) {
	            return ResponseEntity.ok(horario.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @GetMapping("/buscar-por-grupo/{idGrupo}")
	    public ResponseEntity<List<HorarioGrupo>> buscarPorGrupo(@PathVariable int idGrupo) {
	        List<HorarioGrupo> horarios = service.buscarPorGrupo(idGrupo);
	        if (horarios.isEmpty()) {
	            return ResponseEntity.notFound().build();
	        } else {
	            return ResponseEntity.ok(horarios);
	        }
	    }
	    
	    
	    // Actualizar
	    @PutMapping("/actualizar")
	    public ResponseEntity<String> actualizarHorarioGrupo(@Valid @RequestBody HorarioGrupo bean) {
	        Optional<HorarioGrupo> existente = service.buscarPorId(bean.getIdHorario());
	        if (existente.isPresent()) {
	            service.actualizar(bean);
	            return ResponseEntity.ok("Horario actualizado correctamente");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // Eliminar
	    @DeleteMapping("/eliminar/{id}")
	    public Map<String, String> eliminarHorarioGrupo(@PathVariable int id) {
	        try {
	            service.eliminar(id);
	            return Map.of("mensaje", "Horario eliminado correctamente.");
	        } catch (RuntimeException ex) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
	        }
	    }
	    
}
