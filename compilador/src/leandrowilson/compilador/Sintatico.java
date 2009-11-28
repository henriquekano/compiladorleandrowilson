package leandrowilson.compilador;

public class Sintatico {
	List listaDeTokens;
	List erros = new List();
	Integer ponteiroTokem = -1;
	
	//Maquinas De Analise Sintatica/Semantica
	MaquinaPrograma maquinaPrograma = new MaquinaPrograma();
	MaquinaFuncao maquinaFuncao = new MaquinaFuncao();
	MaquinaProcedimento maquinaProcedimento = new MaquinaProcedimento();
	MaquinaDeclaracao maquinaDeclaracao = new MaquinaDeclaracao();
	MaquinaComando maquinaComando= new MaquinaComando();
	
	public Sintatico(List ListaDeTokens) {
		this.listaDeTokens = ListaDeTokens;
	}
	public List getErros(){
		return erros;
	}
	public Boolean executa(){
		PilhaSintatico pilhaSintatico = new PilhaSintatico();
		Integer estadoAtual=0;
		Integer proximoEstado =0;
		TipoMaquina maquinaAtual = TipoMaquina.PROGRAMA;
		TipoMaquina proximaMaquina= TipoMaquina.PROGRAMA;
		Boolean novaMaquina = false;
		Boolean retornaMaquina = false;
		Token tokemAtual = new Token();
		ElementoSintatico desempilha=new ElementoSintatico();		
		
		while(!pilhaSintatico.empty()){
			if(fimDosTokens()){
				erros.add(new Erro(TipoErro.SINTATICO_FIMDETOKENS_ANTES_DO_FIM_DA_PILHA,tokemAtual));
				return false;
			}
			else{
				tokemAtual= proximoTokem();
			}
			
			switch (maquinaAtual){
				case PROGRAMA:
					proximoEstado 	= maquinaPrograma.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaPrograma.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaPrograma.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaPrograma.retornaMaquina(estadoAtual,tokemAtual);
					break;
				case FUNCAO:
					proximoEstado 	= maquinaFuncao.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaFuncao.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaFuncao.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaFuncao.retornaMaquina(estadoAtual,tokemAtual);
					break;
				case PROCEDIMENTO:
					proximoEstado 	= maquinaProcedimento.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaProcedimento.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaProcedimento.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaProcedimento.retornaMaquina(estadoAtual,tokemAtual);
					break;
				case DECLARACAO:
					proximoEstado 	= maquinaDeclaracao.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina 	= maquinaDeclaracao.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina 	= maquinaDeclaracao.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina 	= maquinaDeclaracao.retornaMaquina(estadoAtual,tokemAtual);
					break;
				
			}
			if (novaMaquina){
				pilhaSintatico.push(estadoAtual,maquinaAtual);
				maquinaAtual = proximaMaquina;
				estadoAtual = 0;
			}
			else if(retornaMaquina){
				desempilha = pilhaSintatico.pop();
				estadoAtual = desempilha.estado;
				maquinaAtual = desempilha.maquina;
			}
			else{
				estadoAtual = proximoEstado;
				maquinaAtual = proximaMaquina;
			}
		}
		if (!fimDosTokens()){
			erros.add(new Erro(TipoErro.SINTATICO_FIMDEPILHA_ANTES_DO_FIM_DOS_TOKENS,tokemAtual));
			return false;
		}
		else{
			return true;
		}
		
	}
private boolean fimDosTokens() {
		return ponteiroTokem==listaDeTokens.tamanho-1;
	}

private Token proximoTokem() {
		ponteiroTokem++;
		return (Token)listaDeTokens.get(ponteiroTokem);
	}

private boolean estadoFinal(TipoMaquina maquinaAtual, Integer estadoAtual) {
		// TODO Auto-generated method stub
		return false;
	}

//	Integer proximoEstado;
//	Token tokemAtual;
//	TipoMaquina maquinaAtual;
//	maq1
//	maq2
//	ma3
//	while(true){
//		
//		proximoEstado= maquinaPrograma.proximoEstado(1, new Token());
//	}
//	
//	Integer proximoestado(TipoMaquina maquina, estadoAtual){
//			switch case
//				Tipo1
//					proximoestado = maq1.proximoEstad(estadoAtual,token))
//					proxiMaq = maq1.proximamaq(estadoAtual,token)
//			
//					
//					
//				end swtich
//				if ultimoestado
//					if MaquinaAtual = MaquinaPrograma
//						
//						sysout("IFM DO PROGRAMA - ARQUIVO BINARIO DA MVN CRIADO mvn.codigo")
//					else
//						proximo = pilha.pop();	
//						PROXIMOestado(proxiomo.maquina,proximo.estado);
//					
//				else if chamaOutraMaquina
//					push(new proximo(maquina,proximoestado));
//					proximoestado(proximamaquina,0);				
//				
//				else 
//				proximoestado(maquinaAtual,proximoaEstado)
//				
//				if MaquinaAtual = MaquinaPrograma && Acabou
//
//	}

	private Token nextTokem() {
		// TODO Auto-generated method stub
		return null;
	}

}
