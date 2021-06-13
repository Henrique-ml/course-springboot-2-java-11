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
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		
		// - .getOne(): instancia um objeto "User" mas sem utilizar o banco de dados, deixando este objeto monitorado pelo JPA...
		// ... para trabalharmos com ele e só posteriormente efetuar alguma operação com o banco de dados...
		// ... ou seja, ele só prepara o objeto
		// Sendo melhor do que utilizarmos o método do Repository "findById()" que necessariamente vai ao bd consultar um objeto com dado ID
		
		// - entity: um objeto monitorado pelo JPA
		User entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		
		// Nem todos os atributos do objeto "User" aceitaremos que sejam atualizados, como o ID e a senha, por exemplo
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
