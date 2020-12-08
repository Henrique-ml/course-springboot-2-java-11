package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educandoweb.course.entities.User;

// Registro de Componente do Spring
// Mas nesse caso é opcional, pois essa classe "UserRepository" já herda a classe "JpaRepository" que já está registrado como Componente do Spring
@Repository
public interface UserRepository extends JpaRepository<User, Long> { 

}
