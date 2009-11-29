package leandrowilson.compilador;
import java.util.Hashtable;

public class TabelaDeSimbolos extends Hashtable{

	Hashtable table = new Hashtable();
	
	void AddToken(Token token){
		switch (token.tipo){
		case ID:
			break;
			
		case STRING:
			break;
			
		case NUMERO:
			break;
			
		default:
			//ERRO
			break;
		}
	}
	
	
	
	
	
	
}
