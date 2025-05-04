package com.proyecto.excepciones;

public class DocenteNoEncontradoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocenteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
