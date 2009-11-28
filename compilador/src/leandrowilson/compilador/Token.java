package leandrowilson.compilador;


public class Token {
	public TipoToken tipo;
	public String valor;
	
	public Token(String strValor, TipoToken tipoToken) {
		valor = strValor;
		tipo = tipoToken;
	}
	
//	public Token(String tokenBuffer, String tipoToken) {
//		valor = tokenBuffer;
//		tipo = new TipoToken(tipoToken);
//	}
	
	public Token(String token) {
		tipo = TipoToken.QUEBRADO;
		valor = token;
	}
	
	public Token(){

	}

	public Token(TipoToken tipoToken) {
		tipo = tipoToken;
		valor ="";
	}
}