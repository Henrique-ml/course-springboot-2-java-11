// Repositório de dados

// Classe pertence à camada - (Logical Layers) - Data Access Layer (data repositories)
// Classe que reutilizará o JPA Repository - Spring Data JPA (sub-framework do ecossistema Spring)
// Classe responsável por fazer operações com a entidade User, ou seja, objeto que terá várias operações para se trabalhar com a entidade  User

// Repositories serão interfaces, pois o JPA Repository é uma interface

package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

// Nesse caso específico não será preciso criar a implementação dessa interface, pois...
// o Spring Data JPA já tem uma implementação padrão para essa interface
// Caso seja definido - extends JpaRepository<T, ID> utilizando a sua entidade e o tipo do ID da entidade, já terá uma implementação padrão para esse tipo específico definido
public interface UserRepository extends JpaRepository<User, Long> { // - JpaRepository<tipo da entidade, tipo do ID dessa entidade>

}
