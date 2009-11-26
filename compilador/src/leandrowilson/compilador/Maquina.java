package leandrowilson.compilador;

public class Maquina {
	public Integer estadoInicial;
	public Integer estadoFinal;
	Integer[][] tabelaTransicao ;
	
	Boolean run(){
		
		return true;
	}
	public Integer proximoEstado(Integer estadoAtual, Token entrada){	
		return tabelaTransicao[estadoAtual][entrada.tipo.valor()];
		
	}
public TipoMaquina proximaMaquina(Integer estadoAtual, Token entrada){
		return tabelaTransicao[estadoAtual][entrada.tipo.valor()];
		
	}

public Boolean empilha(Integer estadoAtual, Token entrada){
	return null;
	
}
	
}
