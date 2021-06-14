package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	// --- Tratando a Exceção de Service ao deletar um objeto do banco de dados ---
	
	// código HTTP 50 Internal Server Error: um cósigo não tratado
	
	// Ao tentarmos excluir um objeto que não existe no banco de dados, é retornado como resposta da requisição o código 500 Internal Server Error...
	// ... este erro é lançado aqui
	public void delete(Long id) {
		
		// Capturar a Exceção que é lançada quando o objeto com o ID para deleção não é encotrado
//		try {
//			repository.deleteById(id);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
		
		// Problema 1) - código 500 lançado quando o objeto com o ID para exclusão não era achado
		
		// Agora, quando a Exceção que capturamos for lançada, enviaremos uma resposta da requisição com o código 404 Not Found
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
			
		// Problema 2) - código 500 lançado por não conseguir deletar um objeto "User" por estar atrelado a um objeto "Order" no banco de dados...
		// ... lembrando que não iremos alterar essa lógica para nosso banco de dados não perder a integridade
			
		// Agora, lançamos a NOSSA Exceção personalizada da camada de Service que criamos, a "DatabaseException", e não mais a Exceção do sistema
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
