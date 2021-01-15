package com.educandoweb.course.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.educandoweb.course.entities.pk.OrderItemPK;

// Mapemanto JPA
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L; 

	// Atributo identificador - que é o correspondente da chave-primária composta
	// Não colocar a Annotation @Id, por ser um id composto. Então nesse caso coloca-se a Annotation @EmbeddedId
	@EmbeddedId
	private OrderItemPK id;
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	// O Construtor também irá receber como argumento um objeto tipo "Order" e "Product"
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	// Fazer os GETTERS/SETTERS dos objetos "order" e "product"...
	// ...embora não tenha diretamente esses objetos como atributos, tenho somente o atributo "id"...
	// ...para as outras classes um objeto tipo "OrderItem" não tem os métodos - getId() / -setId()...
	// ...então, terá que ter esses GETTERS/SETTERS para os dois objetos "order" e "product" ligados ao "id" (atributos do atributo "id")
	// ...assim retornará um objeto de cada vez [campo simples - getOrder() ], e não os dois de uma vez [campo composto - getId() ]
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProductr() {
		return id.getProduct();
	}
	
	public void setOrdeProduct(Product product) {
		id.setProduct  (product);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

/// Somente o atributo "id", pois é ele que identifica um objeto tipo "OrderItem" 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
