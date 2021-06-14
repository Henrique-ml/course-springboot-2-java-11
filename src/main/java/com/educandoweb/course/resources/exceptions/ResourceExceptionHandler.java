package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

// É nessa classe que iremos fazer o Tratamento Manual para nossos erros

// - @ControllerAdvice: intercepta as Exceções que serem lançadas para que esse objeto (classe) possa executar um possível tratamento
@ControllerAdvice
public class ResourceExceptionHandler {

	// - @ExceptionHandler: intercepta/capturar a requisição que lançou a Exceção para ser tratada no método
	// - (ResourceNotFoundException.class): o tipo de Exceção que queremos interceptar quando for lançada pelo sistema
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		// Para retornar uma resposta com um código personalizado
		return ResponseEntity.status(status).body(err);
	}
}
