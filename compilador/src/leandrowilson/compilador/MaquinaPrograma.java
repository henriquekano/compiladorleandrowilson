package leandrowilson.compilador;

public class MaquinaPrograma extends Maquina {

	MaquinaPrograma(){
		inicializarMaquina(4,0,3);
		carregarTabelaDeTransicao();
	}
	


	@Override
	public void carregarTabelaDeTransicao() {
		carregarEntradaNaTabelaDeTransicao(0,TipoToken.PR_MAIN,1);
		carregarEntradaNaTabelaDeTransicao(1,TipoToken.CHAVE_ABRE,2);
		carregarEntradasDeSubmaquina(2,TipoMaquina.DECLARACAO,2);
		carregarEntradasDeSubmaquina(2,TipoMaquina.COMANDO,2);
		carregarEntradaNaTabelaDeTransicao(2,TipoToken.CHAVE_FECHA,3);
		carregarEntradasDeSubmaquina(2,TipoMaquina.FUNCAO,3);
		carregarEntradasDeSubmaquina(2,TipoMaquina.PROCEDIMENTO,3);
	}

	



	@Override
	public Boolean novaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoMaquina proximaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer proximoEstado(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean retornaMaquina(Integer estadoAtual, Token tokemAtual) {
		// TODO Auto-generated method stub
		return null;
	}

}
