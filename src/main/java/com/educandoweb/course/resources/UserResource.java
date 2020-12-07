// Controlador Rest - responde no caminho /users

// Classe criada para testar se o Rest da aplicação Spring Boot está funcionando
// Classe pertence à camada - (Logical Layers) - Resource layer (rest controllers)

// Recurso básico baseado na classe User
// Classe disponibiliza um recurso web correspondente a classe User
package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

// Para indicar que a classe é um Recurso Web que é implementado por um Controlador Rest, coloca-se uma anotação
@RestController

// Além disso, dar um nome ao Recurso
// - value = "": caminho do meu Recurso. Nesse caso é um Recurso relacionado à entitade User
@RequestMapping(value = "/users")
public class UserResource {

	// Para indicar que esse método responde a requisição do tipo Get do HTTP
	@GetMapping
	// Para testar se está funcionando mesmo esse Recurso /users, criar um método
	// Método, que é um endpoint, responsável por acessar os usuários
	// - ResponseEntity<T>: tipo específico do Spring para retornar respostas de requisições Web. Nesse caso o tipo de resposta vai ser a classe User
	public ResponseEntity<User> findAll() {
		
		// Teste
		User u = new User(1L, "Maria", "maria@gmail.com", "999999999", "12345");
		
		// - .ok(): para retornar a resposta com sucesso no HTTP
		// - .body(): para retornar o corpo da resposta. Nesse caso o objeto u
		return ResponseEntity.ok().body(u);
	}
}
