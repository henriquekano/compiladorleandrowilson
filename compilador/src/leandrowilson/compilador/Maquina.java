package leandrowilson.compilador;

public abstract class Maquina {
	public  Integer estadoInicial;
	public Integer[] estadosFinais;
	Integer[][] tabelaTransicao ; //Tabela de Transicao de Estados
	TipoMaquina[][] tabelaTransicaoMaquinas;
	Boolean[][] tabelaNovaMaquina;
	
	
	public Integer  QUANTIDADE_DE_TIPOTOKENS = TipoToken.tamanho();
	public final Integer ESTADO_ERRO_SINTATICO = 999999;
	
	
	public abstract Integer proximoEstado(Integer estadoAtual, Token tokemAtual);
	public abstract TipoMaquina proximaMaquina(Integer estadoAtual, Token tokemAtual);
	public abstract Boolean novaMaquina(Integer estadoAtual, Token tokemAtual);
	public abstract void carregarTabelaDeTransicao();
	public abstract Boolean retornaMaquina(Integer estadoAtual, Token tokemAtual);
	public void inicializarMaquina(int quantidadeDeEstados,Integer _estadoInicial,Integer[] _estadosFinais) {
		estadoInicial = _estadoInicial;
		estadosFinais = _estadosFinais;
		tabelaTransicao = new Integer[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		tabelaTransicaoMaquinas = new TipoMaquina[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		tabelaNovaMaquina = new Boolean[quantidadeDeEstados][QUANTIDADE_DE_TIPOTOKENS];
		
		for (int k =0;k<quantidadeDeEstados;k++){
			for (int j=0;j<QUANTIDADE_DE_TIPOTOKENS;j++){
				tabelaTransicao[k][j] = ESTADO_ERRO_SINTATICO;
				tabelaTransicaoMaquinas[k][j] = TipoMaquina.PROGRAMA;
				tabelaNovaMaquina[k][j] = false;
			}
		}
	}
	
	public void inicializarMaquina(int quantidadeDeEstados, int estadoInicial,int estadoFinal) {
		Integer[] estadosFinais = {estadoFinal};
		inicializarMaquina(quantidadeDeEstados, estadoInicial, estadosFinais);
		
	}
	
//	public void inicializarMaquina(int quantidadeDeEstados, int estadoInicial,Integer... estadosFinais) {
//		inicializarMaquina(quantidadeDeEstados, estadoInicial, estadosFinais);
//		
//	}
	public Boolean estadoErro(Integer estado){
		return estado==ESTADO_ERRO_SINTATICO;
		
	}
	public void carregarEntradaNaTabelaDeTransicao(Integer estadoAtual, TipoToken token,Integer proximoEstado, TipoMaquina proximaMaquina, boolean chamaNovaMaquina) {
		tabelaTransicao[estadoAtual][token.valor()]= proximoEstado;
		tabelaTransicaoMaquinas[estadoAtual][token.valor()]= proximaMaquina;
		tabelaNovaMaquina[estadoAtual][token.valor()]=chamaNovaMaquina;
	}
	public void carregarEntradasDeSubmaquina(Integer estadoAtual, TipoMaquina maquina,Integer proximoEstado) {
		TipoToken[] first = maquina.first();
		for (int i = 0;i<first.length;i++){
			carregarEntradaNaTabelaDeTransicao(estadoAtual, first[i], proximoEstado,maquina,true);
		}
		
	}
	
}
