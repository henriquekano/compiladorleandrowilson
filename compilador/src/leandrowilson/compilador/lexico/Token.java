package leandrowilson.compilador.lexico;

public class Token {
	public String tipo;
	public String valor;
	
	public Token(String tokenBuffer, TipoToken tipoToken) {
		valor = tokenBuffer;
		tipo = tipoToken.toString();
	}
	
	public Token(String tokenBuffer, String tipoToken) {
		valor = tokenBuffer;
		tipo = tipoToken;
	}
	
	public Token(String tokenBuffer) {
		tipo = tokenBuffer;
		valor = "";
	}
	
	public Token(){

	}
}
