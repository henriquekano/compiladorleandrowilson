package leandrowilson.compilador;

public class Sintatico {
	List listaDeTokens;
	Integer ponteiroTokem = 0;
	MaquinaPrograma maquinaPrograma = new MaquinaPrograma();

	
	public Sintatico(List ListaDeTokens) {
		this.listaDeTokens = ListaDeTokens;
	}

	public Boolean executa(){
		PilhaSintatico pilhaSintatico = new PilhaSintatico();
		Integer estadoAtual=0;
		Integer proximoEstado =0;
		TipoMaquina maquinaAtual = TipoMaquina.PROGRAMA;
		TipoMaquina proximaMaquina= TipoMaquina.PROGRAMA;
		Boolean novaMaquina = false;
		Boolean retornaMaquina = false;
		Token tokemAtual=(Token) listaDeTokens.get(0);
		ElementoSintatico desempilha=new ElementoSintatico();		
		
		while(!pilhaSintatico.empty()){
			switch (maquinaAtual){
				case PROGRAMA:
					proximoEstado = maquinaPrograma.proximoEstado(estadoAtual, tokemAtual);
					proximaMaquina = maquinaPrograma.proximaMaquina(estadoAtual, tokemAtual);
					novaMaquina = maquinaPrograma.novaMaquina(estadoAtual, tokemAtual);
					retornaMaquina = maquinaPrograma.retornaMaquina(estadoAtual,tokemAtual);
				case FUNCAO:
				case PROCEDIMENTO:
				case DECLARACAO:
				
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
		return true;
		
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
