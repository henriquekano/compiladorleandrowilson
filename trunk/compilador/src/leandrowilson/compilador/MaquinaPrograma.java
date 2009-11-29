package leandrowilson.compilador;

public class MaquinaPrograma extends Maquina {
	
	MaquinaPrograma(){
		tipo=TipoMaquina.PROGRAMA;
		String strTransicoes = "initial:0%"+
		"final:3%"+
		"(0,\"main\")->1%"+
		"(1,\"{\")->2%"+
		"(2,declaracao)->2%"+
		"(2,comando)->2%"+
		"(2,\"}\")->3%"+
		"(3,funcao)->3%"+
		"(3,procedimento)->3";
		inicializarMaquina(4,strTransicoes,this.tipo);
		
	}
	


//	@Override
//	public void carregarTabelaDeTransicao() {	
		
		
//		carregarEntradaNaTabelaDeTransicao(0,TipoToken.PR_MAIN,1,TipoMaquina.PROGRAMA,false);
//		carregarEntradaNaTabelaDeTransicao(1,TipoToken.CHAVE_ABRE,2,TipoMaquina.PROGRAMA,false);
//		carregarEntradasDeSubmaquina(2,TipoMaquina.DECLARACAO,2);
//		carregarEntradasDeSubmaquina(2,TipoMaquina.COMANDO,2);
//		carregarEntradaNaTabelaDeTransicao(2,TipoToken.CHAVE_FECHA,3,TipoMaquina.PROGRAMA,false);
//		carregarEntradasDeSubmaquina(2,TipoMaquina.FUNCAO,3);
//		carregarEntradasDeSubmaquina(2,TipoMaquina.PROCEDIMENTO,3);
//	}

	@Override
	public Integer proximoEstado(Integer estadoAtual, Token tokemAtual) {
		return tabelaTransicao[estadoAtual][tokemAtual.tipo.valor()];
	}

}
