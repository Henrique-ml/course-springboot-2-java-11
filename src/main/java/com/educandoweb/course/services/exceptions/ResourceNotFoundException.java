package com.educandoweb.course.services.exceptions;

// Essa é uma classe para criação de nossa própria Exceção personalizada para a camada de Service
// A camada de Service terá de ser capaz de lançar suas próprias Exceções, e não deixar outras Exceções diversas estourarem

// - RuntimeException: classe de tipo de Exceção que o compilador não nos obriga a tratar
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id = " + id);
	}
}
