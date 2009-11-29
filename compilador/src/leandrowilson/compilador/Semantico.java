package leandrowilson.compilador;

public class Semantico {
	Integer contadorEscopo =0;
	
	
	public ElementoSemantico analisa(ElementoSemantico elSemantico) {
		Escopo escopo = elSemantico.escopo;
		TipoMaquina tipoMaquina = elSemantico.tipoMaquina;
		Integer transicaoSemantica =elSemantico.transicaoSemantica;
		PilhaSemantico pilhaSemantico = elSemantico.pilhaSemantico;
		if(transicaoSemantica==-1){
			return elSemantico;
		}
		
		switch (tipoMaquina) {
			case PROGRAMA:
				switch (transicaoSemantica) {
				case 0:
					
					break;

				default:
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
		
		return null;
	}

}
