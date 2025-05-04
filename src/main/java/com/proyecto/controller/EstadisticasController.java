package com.proyecto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.repositorio.AlumnoRepository;
import com.proyecto.repositorio.CursoRepository;
import com.proyecto.repositorio.DocenteRepository;
import com.proyecto.repositorio.MatriculaRepository;

@RestController
@RequestMapping("/estadistica")
public class EstadisticasController {
	 @Autowired
	    private AlumnoRepository alumnoRepository;

	    @Autowired
	    private CursoRepository cursoRepository;

	    @Autowired
	    private DocenteRepository docenteRepository;

	    @Autowired
	    private MatriculaRepository matriculaRepository;

	    @GetMapping("/es")
	    public Map<String, Long> obtenerEstadisticas() {
	        Map<String, Long> stats = new HashMap<>();
	        stats.put("alumnos", alumnoRepository.count());
	        stats.put("cursos", cursoRepository.count());
	        stats.put("docentes", docenteRepository.count());
	        stats.put("matriculas", matriculaRepository.count());
	        return stats;
	    }
}
