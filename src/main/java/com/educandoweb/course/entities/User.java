package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	// ------ LAZY LOADING ------ //

	// O Lazy Loading é necessário para não estourar a memória da sua máquina causado pela associação de mão-dupla

	// Associação de mão-dupla: onde objetos associados ficam chamando um ao outro em um loop infinito...
	// ...Nesse caso, o objeto "client" tem "orders" e esse objetos "orders" tem um objeto "client"... e por assim vai

	// - @JsonIgnore: para cancelar esse loop infinito causado pela associação de mão-dupla (é uma Annotation do Jackson e não do JPA)

	// Ao carregar/consultar um objeto automaticamente o JPA também carregará o objeto associado à ele...
	// ...Nesse caso ao carregar/consultar um objeto "Order" o JPA carregará o objeto "User" associado à ele
	
	// A biblioteca que faz a Serialização do JSON é o Jackson, e ele pode lançar um erro pois...
	// ...pode haver uma associação de mão-dupla entre objetos que estão associados. Nesse caso, os objetos "client" da classe "Order" com o objeto "orders" da classe "User"
	// O Jackson que faz o pedido ao banco de dados e aí sim o JPA busca esses dados
	
	// Na arquivo src/main/sources > application.properties
	// - spring.jpa.open-in-view=true: permite que o Jackson, na hora de Serializar o JSON, acione o JPA para trazer dados do banco de dados...
	// ...Nesse caso, quando o obbjeto "orders" for chamado, carregar dados do objeto "client" associado à ele
	
	// Quando se tem uma associação "Muitos para um" se ao carregar um objeto do lado do "Muitos" o objeto do lado do "Um" é carregado pelo JPA automaticamente
	// Agora, caso carregar o objeto do lado do "Um" o JPA não carregará também o objeto do lado "Muitos" por padrão...
	// ...a menos que você coloque a Annotation "@JsonIgnore" no lado do "Muitos"
	// Colocar essa Annotation em pelo menos em um dos dois objetos que estão associados
	@JsonIgnore
	
	// --- Diagrama UML "Um (User) para muitos (Order)" -- //
	// Mapear o relacionamento entre o objeto "client" da classe "Order" com o obejto "orders" da classe "User"
	// Para assim o JPA transformar esse relacionamento em chaves estrangeiras no banco de dados
	// - (mappedBy = "client"): nome do atributo que tem lá do outro lado da associação
	@OneToMany(mappedBy = "client")
	// Lista de pedidos dos Users
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Em uma coleção implementa-se somente o - get(): nesse caso para acrescentar e remover elementos 
	// E não trocar essa lista por outra - set()
	public List<Order> getOrders() {
		return orders;
	}
		
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
