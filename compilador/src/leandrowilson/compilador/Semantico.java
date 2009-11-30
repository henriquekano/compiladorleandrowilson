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
		Token tempToken;
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
					analisaToken(pilhaSemantico.pop().tipo,TipoToken.CHAVE_ABRE);
					analisaToken(pilhaSemantico.pop().tipo,TipoToken.PR_MAIN);
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
				case 0:	//(0, "int") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 1://(0, "float") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 2:	//(0, "string") -> 1	
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 3:	//(0, "boolean") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 4:	//(0, "declare") -> 2
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 5:	//(0, "struct") -> 3
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 6:	//(1, "[") -> 4	
					break;
				case 7:	//(1, "identificador") -> 5
					String chave = token.valor;
					Token t ;
					List indices = new List();
					t = pilhaSemantico.pop();
					while (!t.tipo.ehTipoPrimitivo()){
						analisaToken(t.tipo, TipoToken.NUMERO);
						indices.add(new Integer(t.valor));
						t = pilhaSemantico.pop();
					}
					switch(t.tipo){
						case PR_INT:
//							escopo.AddSimbolo(chave, new Descritor(geraLabel(escopo.id_escopo,chave),))
							break;
						case PR_BOOLEAN:
							
							break;
						case PR_STRING:
							
							break;
						}
					break;
				case 8:	//(2, "identificador") -> 1	
					//TODO NAO TRATADO AINDA - DECLARE
					break;
				case 9:	//(3, "identificador") -> 6	
					//TODO NAO TRATADO AINDA - STRUCT
					break;
				case 10://(4, "inteiro") -> 10		
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 11://(4, "identificador") -> 10					
					break;
				case 12://(5, ";") -> 7					
					break;
				case 13://(6, "{") -> 8					
					break;
				case 14://(8, "int") -> 9					
					break;
				case 15://(8, "identificador") -> 9					
					break;
				case 16://(8, "float") -> 9					
					break;
				case 17://(8, "string") -> 9					
					break;
				case 18://(8, "boolean") -> 9					
					break;
				case 19://(8, "}") -> 7					
					break;
				case 20://(9, "[") -> 11					
					break;
				case 21://(9, "identificador") -> 12					
					break;
				case 22://(10, "]") -> 1	
					//IGNORA COLCHETE
					break;
				case 23://(11, "inteiro") -> 13					
					break;
				case 24://(11, "identificador") -> 13					
					break;
				case 25://(12, ";") -> 8					
					break;
				case 26://(13, "]") -> 9					
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

	private String geraLabel(Integer idEscopo, String chave) {
		return "E"+ idEscopo.toString()+"_"+chave;
	}

	private void analisaToken(TipoToken lido, TipoToken esperado) {
		if (!lido.equals(esperado)){
			erroSemantico(lido,esperado);
		}
	}

	private void erroSemantico(TipoToken lido, TipoToken esperado) {
		Util.Log("ErroSemantico");
		
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
