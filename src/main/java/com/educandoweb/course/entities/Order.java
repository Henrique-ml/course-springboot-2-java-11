package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

//São feitas Annotations do JPA para instruir o JPA como que ele converterá os objetos para o modelo relacional
// Annotations do JPA para instruir ao JPA que essa classe "Order" será uma tabela do banco de dados 
// - @Entity
// - @Id
// - @GeneratedValue

@Entity

// Colou-se essa Annotation pois "Order" é uma palavra reservada do SQL
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	// Chave primária da tabela do banco de dados (H2 - banco de dados em memória)	
	@Id	
	// Como esse Chave é uma Chave numérica, ela será autoincrementável no banco de dados	
	// Dependendo do banco de dados usado a expressão dentro do "()" terá que mudar. Mas para os principais como MySQL, H2, etc irá funcionar	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Para garantir que o objeto "moment" seja mostrado no JSON no formato de String ISO 8601 acrescenta-se uma Annotation para formatar o JSON
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	// Antigamente antes da Java 8 se usava o tipo "Date"
	// A partir do Java 8 deu-se proferência ao tipo "Instant" por ser melhor que o tipo "Date"
	private Instant moment;
	
	//  -- Diagrama UML "Muitos (Order) para um (User)" -- //
	// Mapear o relacionamento entre o objeto "client" da classe "Order" com o obejto "orders" da classe "User"
	// Para assim o JPA transformar esse relacionamento em chaves estrangeiras no banco de dados
	@ManyToOne
	
	// - (name = "client_id"): nome da chave estrangeira que terá lá no banco de dados
	@JoinColumn(name = "client_id")
	private User client;
	
	public Order() {
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	// Comparar um objeto com outro somente por Id
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
