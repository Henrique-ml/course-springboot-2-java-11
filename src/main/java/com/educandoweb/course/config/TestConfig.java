// Auxiliar de configurações da Aplicação

// Classe por enquanto servirá para se fazer o Database Seeding (iniciar e popular o banco de dados com alguns objetos - instanciação do banco de dados)

package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

// Para indicar ao Spring que é uma classe específica de configuração, coloca-se uma annotation
@Configuration

// Além disso, para indicar que essa classe é uma configuração específica par o perfil de teste, coloca-se outra annotation
// - ("test"): esse nome deve ser igual ao valor da propriedade "spring.profiles.active" do arquivo "application.properties"
// Portanto, o 	Spring consegue identificar que ele vai rodar essa configuração somente quando se estiver no perfil de teste
@Profile("test")

// - implements CommandlineRunner: macete que implementa o método "run()", e que dentro desse método terá comandos à serem executados quando a aplicação for iniciada
public class TestConfig implements CommandLineRunner{

	// Como se está usando um Framework a Injeção de dependência é automática
	// Geralemete o Framework tem o mecanismo de Injeção de dependência implícito (automático). E no caso do Spring não é diferente
	
	// Para fazer um objeto depender de outro, faça:
	// 1) declarar a dependência
	// 2) para que o Spring consiga resolver essa dependência e associar uma instância do UserRepository na classe TestConfig, coloca-se uma annotation
	
	// 2)
	@Autowired
	// 1)
	// - userRepository: objeto Repository que acessa os dados
	private UserRepository userRepository;

	// Comandos à serem executados quando a aplicação for iniciada
	@Override
	public void run(String... args) throws Exception {

		// Mapeamento objeto-relacional e Database Seeding a partir desses objetos instanciados manualmente	
		// - (null, ...): o Id é null porque o Id já é gerado automaticamente pelo banco de dados
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 

		// Salvar esses objetos "u1" e "u2" no banco de dados
		// - userRepository: objeto Repository que acessa os dados 
		// - saveAll(): salva uma lista de obejtos ao banco de dados
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
	
}
