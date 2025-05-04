package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidades.Alumno;
import com.proyecto.entidades.Docente;
import com.proyecto.excepciones.AlumnoNoEncontradoException;
import com.proyecto.excepciones.DocenteNoEncontradoException;
import com.proyecto.repositorio.DocenteRepository;

@Service
public class DocenteServices {

    @Autowired
    private DocenteRepository repo;

    // Listar Docentes
    public List<Docente> listarDocentes() {
        return repo.findAll();
    }

    // Insertar o actualizar Docente
    public void registrarDocente(Docente bean) {
        repo.save(bean);
    }

    // Buscar Docente por ID
    public Docente buscarPorId(Integer id) {
        Optional<Docente> docente = repo.findById(id);
        if (docente.isPresent()) {
            return docente.get();
        } else {
            throw new DocenteNoEncontradoException("Docente con ID: " + id + " no encontrado");
        }
    }

    // Eliminar Docente
    public void eliminarPorId(Integer id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("El docente con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }
    
    
    public Docente actualizarDocente(Integer id, Docente docenteActualizado) {
	    // Primero verificamos si el alumno existe
	    if (!repo.existsById(id)) {
	        throw new DocenteNoEncontradoException("Alumno con ID: " + id + " no encontrado");
	    }

	    // Si el alumno existe, simplemente guardamos el alumno actualizado
	    docenteActualizado.setIdDocente(id); // Aseguramos que el id se mantenga igual
	    return repo.save(docenteActualizado); // El método save de JPA actualizará automáticamente
	}
    
}
