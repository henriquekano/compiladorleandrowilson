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

	public static TipoToken palavraReservada(String palavraReservada) {
		if (palavraReservada.equals("true"))
			return TipoToken.PR_TRUE;
		if (palavraReservada.equals("false"))
			return TipoToken.PR_FALSE;
		if (palavraReservada.equals("int"))
			return TipoToken.PR_INT;
		if (palavraReservada.equals("float"))
			return TipoToken.PR_FLOAT;
		if (palavraReservada.equals("string"))
			return TipoToken.PR_STRING;
		if (palavraReservada.equals("boolean"))
			return TipoToken.PR_BOOLEAN;
		if (palavraReservada.equals("procedure"))
			return TipoToken.PR_PROCEDURE;
		if (palavraReservada.equals("callproc"))
			return TipoToken.PR_CALLPROC;
		if (palavraReservada.equals("procedure"))
			return TipoToken.PR_PROCEDURE;
		if (palavraReservada.equals("function"))
			return TipoToken.PR_FUNCTION;
		if (palavraReservada.equals("callfunc"))
			return TipoToken.PR_CALLFUNC;
		if (palavraReservada.equals("if"))
			return TipoToken.PR_IF;
		if (palavraReservada.equals("else"))
			return TipoToken.PR_ELSE;
		if (palavraReservada.equals("while"))
			return TipoToken.PR_WHILE;
		if (palavraReservada.equals("input"))
			return TipoToken.PR_INPUT;
		if (palavraReservada.equals("output"))
			return TipoToken.PR_OUTPUT;
		if (palavraReservada.equals("return"))
			return TipoToken.PR_RETURN;
		return TipoToken.QUEBRADO;
	}

	public static String operador(String string2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
