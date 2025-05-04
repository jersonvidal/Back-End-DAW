package com.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.entidades.DetalleMatricula;
import com.proyecto.services.DetalleMatriculaService;

@RestController
@RequestMapping("/detallematricula")
@CrossOrigin
public class DetalleMatriculaController {

	 @Autowired
	    private DetalleMatriculaService service;
	   
	    @GetMapping("/listar")
	    public List<DetalleMatricula> listar() {
	        return service.listar();
	    }
	    
	    @PostMapping("/registrar")
	    public void registrar(@RequestBody DetalleMatricula bean) {
	        System.out.println("DetalleMatricula recibido: " + bean);

	        service.registrar(bean);
	    }


	    @GetMapping("/matricula/{id}")
	    public List<DetalleMatricula> listarPorMatricula(@PathVariable("id") int idMatricula) {
	        return service.listarPorMatricula(idMatricula);
	    }

	    @GetMapping("/buscar/{id}")
	    public Optional<DetalleMatricula> buscar(@PathVariable("id") int idDetalle) {
	        return service.buscarPorId(idDetalle);
	    }

	    @PutMapping("/actualizar")
	    public void actualizar(@RequestBody DetalleMatricula bean) {
	        service.actualizar(bean);
	    }

	    @DeleteMapping("/eliminar/{id}")
	    public void eliminar(@PathVariable("id") int idDetalle) {
	        service.eliminar(idDetalle);
	    }
}
