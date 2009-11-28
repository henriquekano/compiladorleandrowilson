package leandrowilson.compilador;

public class MaquinaPrograma extends Maquina {

	MaquinaPrograma(){
		inicializarMaquina(4,0,3);
		carregarTabelaDeTransicao();
	}
	


	@Override
	public void carregarTabelaDeTransicao() {
		carregarEntradaNaTabelaDeTransicao(0,TipoToken.PR_MAIN,1,TipoMaquina.PROGRAMA,false);
		carregarEntradaNaTabelaDeTransicao(1,TipoToken.CHAVE_ABRE,2,TipoMaquina.PROGRAMA,false);
		carregarEntradasDeSubmaquina(2,TipoMaquina.DECLARACAO,2);
		carregarEntradasDeSubmaquina(2,TipoMaquina.COMANDO,2);
		carregarEntradaNaTabelaDeTransicao(2,TipoToken.CHAVE_FECHA,3,TipoMaquina.PROGRAMA,false);
		carregarEntradasDeSubmaquina(2,TipoMaquina.FUNCAO,3);
		carregarEntradasDeSubmaquina(2,TipoMaquina.PROCEDIMENTO,3);
	}

	



	@Override
	public Boolean novaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TipoMaquina proximaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return TipoMaquina.PROGRAMA;
	}

	@Override
	public Integer proximoEstado(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return tabelaTransicao[estadoAtual][tokemAtual.tipo.valor()];
	}

	@Override
	public Boolean retornaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return false;
	}

}
