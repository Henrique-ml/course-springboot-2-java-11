package com.educandoweb.course.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

// Essa é uma classe para construir um objeto personalizado com os mesmos atributos do objeto JSON lançado nas Exceções...
// ... que podemos checar no 'Body' retornado das requisições da aplicação

//Classe criada na camada de "resources" pois as Repostas das Requisições são de responsabilidade desta camada

// Objeto JSON de erro retornado automaticamente na Exceção quando não encontramos o ID solicitado:

//	{
//	    "timestamp": "2021-06-13T23:39:27.631+00:00",
//	    "status": 500,
//	    "error": "Internal Server Error",
//	    "message": "",
//	    "path": "/users/1"
//	}

// Então, para que possamos fazer um Tratamento Manual (classe "ResourceExceptionHandler") das Exceções...
// ... e retornar um objeto JSON parecido com esse acima (classe "StandardError"), estamos criando todas essas classes

// - ResourceNotFoundException
// - StandardError
// - ResourceExceptionHandler

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
	}

	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	// Não implementamos o hashCode() e equals() pois em momento algum não nos interessa comparar um erro com outro
}
