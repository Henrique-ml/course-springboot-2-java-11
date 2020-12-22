// Serviço

// Classe pertence à camada - (Logical Layers) - Service layer

// Não é proibido, mas uma preferência de algumas pessoas em fazer uma camada intermediária de Serviço para implementar as regras de negócio, orquestração de Repositories, etc
// Ex: Ao salvar um pedido, eu posso querer verificar o estoque (se há o produto disponível no estoque), etc
// Assim, são várias operações que se coloca-se tudo no Controlador, apenas chamando as operações de salvamento no Repositórios, iria ficar um Controlador muito carregado com regras de negócio
		
// Então o bom é separar as responsabilidades:
// 1) deixar os Controladores Rest enxutos e com o seu papel de intermediar as interações do usuário com as regras de negócio
// 2) as regras de negócio ficam nos Serviços

// Porém, também há algumas "desvantagens" por assim dizer, pois terão muitas operações do Controladores em que a camada de Serviço irá apenas repassar ao Repósitorio a chamada de algo
// Ex: fazer um endpoint para recuperar um usuário por Id

package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
