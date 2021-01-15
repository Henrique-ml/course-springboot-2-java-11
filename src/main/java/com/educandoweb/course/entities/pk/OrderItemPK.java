// Assistir aula 317 0:00 - 2:50

// Classe auxiliar - que é a chave-primária de um objeto tipo "OrderItem"
// Essa classe sim vai ter uma referência para objetos de classes "Product" e "Order"

// - .pk: sempre que se precisar criar uma classe auxiliar para ser uma CHAVE-PRIMÁRIA COMPOSTA, coloca-se nesse pacote
package com.educandoweb.course.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.Product;

// Essa classe não terá Construtores

// Annotation do JPA
// Por ser uma classe auxiliar de chave-primária composta oloca-se a Annotation @Embeddable
@Embeddable
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;

	// Os dois atributos "order" e "product" serão relacionamentos MUITOS-PARA-UM com "Product" e "Order"
	
	@ManyToOne
	
	// - name = : nome da chave-estrangeira na tabela do banco de dados relacional
	@JoinColumn(name = "order_id")
	private Order order;
	
	@ManyToOne
	
	// - name = : nome da chave-estrangeira na tabela do banco de dados relacional
	@JoinColumn(name = "product_id")
	private Product product;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	
	// Nesse caso, para comparar um objeto "OrderItem" tem-se que comparar tanto o atributo "order" quanto o "product"...
	// ...ou seja, porque são os dois de identificam um "OrderItem" 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}
