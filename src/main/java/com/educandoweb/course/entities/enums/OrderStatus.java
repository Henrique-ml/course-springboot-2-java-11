package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	// Com os tipos enumerados desta forma, o Java por padrão atribui um valor numérico em cada um dos valores do tipo enumerado (ENUM)
	// Ex:
	// WAITING_PAYMENT é igual a 0
	// PAID é igual a 1
	// ...
	
	// Isso tem um problema de manutenção. Se deixar essa atribuição de valores automática do Java...
	// ...Se no futuro um programador inexperiente adicionar um novo tipo enumerado no meio, os próximos tipos enumerados (depois dele) terão o valor alterado...
	// ...Ou seja, no banco de dados esses valores ficarão errados
	
	// Então, para evitar esse problema, será atribuido manualmente um valor numérico para cada tipo enumerado...
	// ...Para que dessa, forma quem for dar manutenção no futuro, fique explícito qual valor correspondente a cada tipo enumerado
	// Ex:
	// WAITING_PAYMENT(0)
	// PAID (1)
	// ...
	
	// Porém essa atribuição de valor manual o Java exige a implementação de algumas coisas
	
	WAITING_PAYMENT(1), // uso do Construtor e assim criando objetos
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	// Código do tipo enumerado
	private int code;
	
	// Construtor para o tipo enumerado
	// Como é um Construtor de tipos enumerados, o tipo usado é PRIVATE
	private OrderStatus(int code) {
		this.code = code;
	}
	
	// Para outras classes acessarem esse atributo cria-se um método PUBLIC
	public int getCode() {
		return code;
	}
	
	// Método responsável por converter um valor numérico para o tipo enumerado
	public static OrderStatus valueOf(int code) {
		
		// Percorrer todos os valores enumerados dessa classe
		// - values(): retorna todos os tipos enumerados daquela classe enumerada
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			} 
		}
		
		// Lança a exceção caso o argumento passado não corresponder a nenhum tipo numérico da classe
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
}
