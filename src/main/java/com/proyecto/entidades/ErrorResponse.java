package com.proyecto.entidades;

import lombok.Data;

@Data
public class ErrorResponse {
	private String campo;
	private String mensaje;

	public ErrorResponse(String campo, String mensaje) {
		this.campo = campo;
		this.mensaje = mensaje;
	}
}
