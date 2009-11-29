package leandrowilson.compilador;

public class Escopo {
	Escopo escopoPai;
	TabelaDeSimbolos tabelaDeSimbolos;
	int id_escopo;
	
	Escopo(){
		id_escopo = 0;
		this.escopoPai = null;
		this.tabelaDeSimbolos = new TabelaDeSimbolos();
	}
	
	Escopo(Escopo escopoPai, int id){
		this.id_escopo = id;
		this.escopoPai = escopoPai;
		this.tabelaDeSimbolos = new TabelaDeSimbolos();
	}
	
	void AddSimbolo(Token token){
		tabelaDeSimbolos.AddToken(token);
	}
	
	
}
