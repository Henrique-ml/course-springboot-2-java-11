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

// Component registration - quando se tem um objeto que ele poderá ser injetado pelo mecanismo de Injeção de dependência do Spring...
// ...a classe desse objeto tem que estar registrada como um Componente no mecanismo de Injeção de dependência

// Todo Framework que você usar, seja para back-end ou front-end, esse Framework terá uma forma de se registrar uma classe em seu mecanismo de Injeção de dependência

// Ou seja, para as Dependências das classes "UserService" e "UserRepository" funcionarem elas devem estar registradas como um Componente do Spring por meio de annotations...
// ...para assim serem injetadas automaticamente pela annotation @Autowired em outras classes
// - @Component: registra a classe como um Componente do Spring

// Porém, no Spring, há outras annotations que tem uma semântica mais específica para o que você precisar
// - @RestController
// - @Service: registra a classe como um Serviço na sua camada de Serviços
// - @Repository: registra a classe como um Repositório na sua camada de Repositórios
@Service
public class UserService {

	// Dependência do Repositório
	// - @Autowired: Injeção de dependência automática de modo transparente ao programador
	@Autowired
	private UserRepository repository;
	
	// Método responsável por retornar todos os usuários do banco de dados
	// Operação na camada de Serviço ( - findAll() ) repassando a chamada para a camada de Repositório ( - repository.findAll() )
	public List<User> findAll() {
		return repository.findAll();
	}
	
	// Método responsável por retornar um usuário por determinado Id
	public User findById(Long id) {
		
		// - .findById(): retorna um objeto tipo Optional<T> (que existe desde o Java 8)
		Optional<User> obj = repository.findById(id);
		
		// - .get(): retorna o objeto do tipo definido no generic "Optional<T>". Nesse caso o tipo do objeto "User"
		return obj.get();
	}
}
