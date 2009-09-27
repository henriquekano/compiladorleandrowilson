package leandrowilson.compilador.lexico;

public class Token {
	public TipoToken tipo;
	public String valor;
	
	public Token(String tokenBuffer, TipoToken tipoToken) {
		valor = tokenBuffer;
		tipo = tipoToken;
	}
	
	public Token(){

	}
}
