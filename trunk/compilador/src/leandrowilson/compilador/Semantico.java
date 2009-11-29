package leandrowilson.compilador;

public class Semantico {
	Integer contadorEscopo =0;
	List erros = new List();
	StringBuffer codigoMVN = new StringBuffer();
	
	public ElementoSemantico analisa(ElementoSemantico elSemantico) {
		Escopo escopo = elSemantico.escopo;
		TipoMaquina tipoMaquina = elSemantico.tipoMaquina;
		Integer transicaoSemantica =elSemantico.transicaoSemantica;
		PilhaSemantico pilhaSemantico = elSemantico.pilhaSemantico;
		Token token = elSemantico.token;
		if(transicaoSemantica==-1){
			return elSemantico;
		}
		
		switch (tipoMaquina) {
			case PROGRAMA:
				switch (transicaoSemantica) {
				case 0://(0, "main") -> 1
					pilhaSemantico.push(token);
					
					break;
				case 1://(1, "{") -> 2
					pilhaSemantico.push(token);
					geraCodigo_inicial();
					contadorEscopo++;
					escopo = new Escopo(escopo, contadorEscopo);
					elSemantico.pilhaSemantico = pilhaSemantico;
					elSemantico.escopo = escopo;
					break;
				case 2://(2, declaracao) -> 2

					break;
				case 3://(2, comando) -> 2
					break;
				case 4://(2, "}") -> 3
					escopo = escopo.escopoPai;
					pilhaSemantico.pop();
					break;
				case 5://(3, funcao) -> 3
					break;
				case 6://(3, procedimento) -> 3
					break;
				default:
					erros.add(new Erro(TipoErro.SEMANTICO_TRANS_SEMANTICA_INESPERADA,elSemantico.token));
					break;
				}
				break;
			case FUNCAO:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case PROCEDIMENTO:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case DECLARACAO:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case COMANDO:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case EXPRESSAO:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case EXPBOOLEANA:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case EXPRELACIONAL:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case EXPARITMETICA:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
			case EXPSTRING:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
					break;
				}
				break;
		}
		
		return elSemantico;
	}

	private void geraCodigo_inicial() {
		codigoMVN.append("&/0 \n");
		codigoMVN.append("     JP INICIO \n");
		codigoMVN.append("INICIO ");
		logCodigo();
	}

	private void logCodigo() {
		Util.Log(codigoMVN.toString());
		
	}

}
