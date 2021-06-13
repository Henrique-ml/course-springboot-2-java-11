package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	
	// @RequestBody: objeto que chegar do método "POST" virá no formato JSON e será deserializado...
	// ... sendo convertido para o formato de objeto "User" e apontado no objeto "obj"
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		
		// ---- Forma adequada de se inserir um novo objeto no banco de dados e retornar uma resposta HTTP ----
		
		// Quando se insere um recurso, um novo objeto no banco de dados, a resposta mais HTTP apropriada é a 201 CREATED e não 200 OK
		
		// .created(): o método HTTP Created espera um objeto tipo "URI"...
		// pois o padrão HTTP, quando você retorna um 201, é esperado que a resposta contenha um cabeçalho chamado "location"...
		// ... contendo o endereço do novo recurso que foi inserido (o caminho de URL que poderemos consultar esse novo objeto que salvamos)
		
		// Aqui no Spring Boot temos uma forma padrão de gerar esse endereço
		
		// pacote "java.net.URI"
		
		// - .path(): o padrão para montar a URL...
		// .. nesse caso, o recurso que iremos inserir, será um recurso que terá o caminho "/users/{id novo que iremos inserir}"
		
		// - .buildAndExpand(): espera que informe o ID que inserimos...
		// ... que nesse caso, estará no atributo "id" do meu objeto "obj"
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
}
