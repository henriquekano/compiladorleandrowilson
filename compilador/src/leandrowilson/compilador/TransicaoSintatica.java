package leandrowilson.compilador;

public class TransicaoSintatica {
	Boolean transicaoParaMaquina;
	Integer estadoInicial;
	TipoMaquina maquina;
	TipoToken token;
	Integer proximoEstado;
	
	public TransicaoSintatica(boolean transicaoParaMaquina, String estadoInicial, String entrada,String proximoEstado) {
		this.transicaoParaMaquina = transicaoParaMaquina;
		this.estadoInicial = new Integer(estadoInicial);
		this.proximoEstado = new Integer(proximoEstado);
		if (transicaoParaMaquina){
			maquina = TipoMaquina.tipoMaquina(entrada);
		}
		else{
			token = TipoToken.tipoToken(entrada);
		}
	}

}
