package com.proyecto.excepciones;

public class CursoNoEncontradoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
