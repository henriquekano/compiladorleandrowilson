package leandrowilson.compilador;

public enum TipoToken {
	//TODO Completar os tipos
	ID(0),
	NUMERO(1),
	OPERADOR(2),
	STRING(3),
	PLUS(4),
	MINUS(5),
	PR_TRUE(6),
	PR_FALSE(7),
	PR_INT(8),
	PR_FLOAT(9),
	PR_STRING(10),
	PR_BOOLEAN(11),
	PR_PROCEDURE(12),
	PR_CALLPROC(13),
	PR_FUNCTION(14),
	PR_CALLFUNC(15),
	PR_IF(16),
	PR_ELSE(17),
	PR_WHILE(18),
	PR_INPUT(19),
	PR_OUTPUT(20),
	PR_RETURN(21),
	QUEBRADO(22);
	
	private final Integer valor;
	public Integer valor(){
		return this.valor;
	}
	
	TipoToken(Integer valor){
		this.valor = valor;
	}

}
