package com.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.proyecto.entidades.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<ErrorResponse>> handleConstraintViolationException(ConstraintViolationException ex) {
		// Crear una lista para almacenar los errores de validación
		List<ErrorResponse> errors = new ArrayList<>();

		// Iterar sobre las violaciones (errores de validación) y agregar detalles a la
		// lista
		ex.getConstraintViolations().forEach(violation -> {
			String campo = violation.getPropertyPath().toString();
			String mensaje = violation.getMessage();
			errors.add(new ErrorResponse(campo, mensaje));
		});

		// Retornar el listado de errores con el código de estado 400 (Bad Request)
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	/*
	 * // Maneja excepciones de errores de enlace de datos (por ejemplo,
	 * BindingResult)
	 * 
	 * @ExceptionHandler(org.springframework.validation.BindException.class) public
	 * ResponseEntity<String> handleBindException(BindingResult result) { //
	 * Devolver un mensaje más amigable sobre los errores de validación return new
	 * ResponseEntity<>("Error de validación: " + result.getAllErrors(),
	 * HttpStatus.BAD_REQUEST); }
	 */
}
