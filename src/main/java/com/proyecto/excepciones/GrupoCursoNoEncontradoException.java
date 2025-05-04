package com.proyecto.excepciones;

public class GrupoCursoNoEncontradoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GrupoCursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
