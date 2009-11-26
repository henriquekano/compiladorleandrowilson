package leandrowilson.compilador;

public enum TipoToken {
	//TODO Completar os tipos
	ID(0),
	NUMERO(1),
	OPERADOR(2),
	STRING(3),
	PLUS(4),
	MINUS(5),
	PALAVRA_RESERVADA_TROCARRRRRRRR(6),
	DESCONHECIDO(7);
	
	private final Integer valor;
	public Integer valor(){
		return this.valor;
	}
	
	TipoToken(Integer valor){
		this.valor = valor;
	}

}
