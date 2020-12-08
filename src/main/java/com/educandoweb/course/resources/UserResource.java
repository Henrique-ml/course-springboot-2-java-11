package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	// Dependência do Serviço
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		// Atualização do método
		// Agora será selecionado todos os objetos "User" do banco de dados
		List<User> list = service.findAll();
		
		// Retornar essa lista de objetos "User"
		return ResponseEntity.ok().body(list);
	}
	
	
	// Só que nesse caso será passado na URL o valor do Id do usuário
	// - (value = "/{id}"): indica que minha requisição vai aceitar um Id dentro da URL (URL com parâmetro)
	@GetMapping(value = "/{id}")
	
	// Método, que é um endpoint, responsável por buscar um usuário por Id
	// - findById(): recebe como parâmetro o parâmetro da URL ("/{id}")
	// - @PathVariable: annotation que faz o Spring aceitar e considerar o argumento do método "Long id" como parâmetro que chegará na URL ("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Por enquanto, caso busque na URL um Id que não existe, retorna-se o erro de código HTTP 500, que não é a forma correta de se retornar um erro

}
