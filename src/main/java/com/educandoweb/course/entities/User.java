package com.educandoweb.course.entities;

import java.io.Serializable;

// É sempre bom a classe depender da especificação (javx.persistence0), e não da implementação (org.hibernate). Nesse caso do JPA
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// São feitas Annotations do JPA para instruir o JPA como que ele converterá os objetos para o modelo relacional

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Chave primária da tabela do banco de dados (H2 - banco de dados em memória
	@Id
	// Como esse Chave é uma Chave numérica, ela será autoincrementável no banco de dados
	// Dependendo do banco de dados usado a expressão dentro do "()" terá que mudar. Mas para os principais como MySQL, H2, etc irá funcionar
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
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
