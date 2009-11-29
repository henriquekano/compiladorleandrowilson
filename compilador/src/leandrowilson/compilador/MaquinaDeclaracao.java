package leandrowilson.compilador;

public class MaquinaDeclaracao extends Maquina {
	
	MaquinaDeclaracao(){
		tipo=TipoMaquina.DECLARACAO;
		String strTransicoes = "initial:0%"+
		"final:7%"+
		"(0,\"int\")->1%"+
		"(0,\"float\")->1%"+
		"(0,\"string\")->1%"+
		"(0,\"boolean\")->1%"+
		"(0,\"declare\")->2%"+
		"(0,\"struct\")->3%"+
		"(1,\"[\")->4%"+
		"(1,\"identificador\")->5%"+
		"(2,\"identificador\")->1%"+
		"(3,\"identificador\")->6%"+
		"(4,\"inteiro\")->9%"+
		"(4,\"identificador\")->9%"+
		"(5,\";\")->7%"+
		"(6,\"{\")->8%"+
		"(8,declaracao)->8%"+
		"(8,\"}\")->7%"+
		"(9,\"]\")->1%";
		inicializarMaquina(10,strTransicoes,this.tipo);
		
	}
//	@Override
//	public void carregarTabelaDeTransicao() {
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_INT  	, 1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_FLOAT	, 1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_STRING	, 1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_BOOLEAN	, 1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_DECLARE	, 2,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(0, TipoToken.PR_STRUCT	, 3,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(1, TipoToken.COLCHETE_ABRE,4,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(1, TipoToken.ID			, 5,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(2, TipoToken.ID			, 1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(3, TipoToken.ID			, 6,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(4, TipoToken.NUMERO		, 10,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(4, TipoToken.ID			, 10,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(5, TipoToken.PONTOEVIRGULA,7,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(6, TipoToken.CHAVE_ABRE	,8,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.PR_INT		, 9,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.ID			, 9,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.PR_FLOAT	, 9,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.PR_STRING	, 9,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.PR_BOOLEAN	, 9,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(8, TipoToken.CHAVE_FECHA	,7,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(9, TipoToken.COLCHETE_ABRE,11,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(9, TipoToken.ID			, 12,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(10, TipoToken.COLCHETE_FECHA,1,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(11, TipoToken.NUMERO		, 13,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(11, TipoToken.ID			, 13,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(12, TipoToken.PONTOEVIRGULA,8,this.tipo,false);
//		carregarEntradaNaTabelaDeTransicao(13, TipoToken.COLCHETE_FECHA,9,this.tipo,false);
//	}


	@Override
	public Integer proximoEstado(Integer estadoAtual, Token tokemAtual) {
		return tabelaTransicao[estadoAtual][tokemAtual.tipo.valor()];
	}

	

}
