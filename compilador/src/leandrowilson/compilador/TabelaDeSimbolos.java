package leandrowilson.compilador;
import java.util.Hashtable;

public class TabelaDeSimbolos extends Hashtable{

	Hashtable table = new Hashtable();
	
	public void AddSimbolo(String chave, Descritor descritor) {
		table.put(chave, descritor);
	}
}
