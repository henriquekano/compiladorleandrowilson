package leandrowilson.compilador;


public class Token {
	public TipoToken tipo;
	public String valor;
	
	public Token(String tokenBuffer, TipoToken tipoToken) {
		valor = tokenBuffer;
		tipo = tipoToken;
	}
	
//	public Token(String tokenBuffer, String tipoToken) {
//		valor = tokenBuffer;
//		tipo = new TipoToken(tipoToken);
//	}
	
//	public Token(String tokenBuffer) {
//		tipo = tokenBuffer;
//		valor = "";
//	}
	
	public Token(){

	}
}
