package leandrowilson.compilador;

import javax.swing.text.Position;

public class Semantico {
	Integer contadorEscopo =0;
	List erros = new List();
	StringBuffer codigoMVN = new StringBuffer();
	Integer inicioAreaDeDados = 1000;
	Integer ponteiroAreaDeDados = inicioAreaDeDados-1;
	List reservasDeMemoria = new List();
	
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
					if (escopo.tabelaDeSimbolos.containsKey(chave)){//verifica se a chave ja nao foi inserida na tabela corrente
						//TODO ERRO SEMANTICO - VARIAVEL JA DECLARADA
					}
					else{
						Token t ;
						List indices = new List();
						t = pilhaSemantico.pop();
						while (!t.tipo.ehTipoPrimitivo()){
							analisaToken(t.tipo, TipoToken.NUMERO);
							indices.add(new Integer(t.valor));
							t = pilhaSemantico.pop();
						}
						String label = geraLabel(escopo.id_escopo, chave);
						switch(t.tipo){
							case PR_INT:
								if (indices.tamanho==0){
									Descritor descritor = new Descritor(label, TipoDescritor.VAR_INT);
									escopo.AddSimbolo(chave,descritor);
									Descritor d = escopo.busca(chave);
									d.posicaoInicial = reservaEspacoDeMemoria_VAR_INT(label);
									escopo.AddSimbolo(chave, d);
								}
								else{
									Integer[] indices_vetor = indices.toIntArray();
									Descritor descritor = new Descritor(label,indices_vetor,TipoDescritor.VVAR_INT);
									escopo.AddSimbolo(chave,descritor);
									Descritor d = escopo.busca(chave);
									d.posicaoInicial = reservaEspacoDeMemoria_VVAR_INT(label,produtorio(indices_vetor));
									escopo.AddSimbolo(chave, d);
								}							
								break;
							case PR_BOOLEAN:
								if (indices.tamanho==0){
									Descritor descritor = new Descritor(label, TipoDescritor.VAR_BOOL);
									escopo.AddSimbolo(chave,descritor);
									Descritor d = escopo.busca(chave);
									d.posicaoInicial = reservaEspacoDeMemoria_VAR_BOOL(label);
									escopo.AddSimbolo(chave, d);
								}
								else{
									Integer[] indices_vetor = indices.toIntArray();
									Descritor descritor = new Descritor(label,indices_vetor,TipoDescritor.VVAR_BOOL);
									escopo.AddSimbolo(chave,descritor);
									Descritor d = escopo.busca(chave);
									d.posicaoInicial = reservaEspacoDeMemoria_VVAR_BOOL(label,produtorio(indices_vetor));
									escopo.AddSimbolo(chave, d);
								}			
								break;
							case PR_STRING:
								
								break;
							}
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

	private Integer reservaEspacoDeMemoria_VVAR_BOOL(String label,
			Integer tamanho) {
		ponteiroAreaDeDados++;
		Integer posicaoInicial= ponteiroAreaDeDados;
		for(Integer i=0;i<tamanho;i++){
			reservasDeMemoria.add(label+"_"+i.toString());
		}
		return posicaoInicial;
	}

	private Integer reservaEspacoDeMemoria_VAR_BOOL(String label) {
		ponteiroAreaDeDados++;
		reservasDeMemoria.add(label);
		return ponteiroAreaDeDados;
	}

	private Integer reservaEspacoDeMemoria_VVAR_INT(String label,Integer tamanho) {
		ponteiroAreaDeDados++;
		Integer posicaoInicial= ponteiroAreaDeDados;
		for(Integer i=0;i<tamanho;i++){
			reservasDeMemoria.add(label+"_"+i.toString());
		}
		return posicaoInicial;
	}

	private Integer produtorio(Integer[] vetor) {
		Integer resultado = 1;
		for(int i=0;i<vetor.length;i++){
			resultado=resultado*vetor[i];
		}
		return resultado;
	}

	private Integer reservaEspacoDeMemoria_VAR_INT(String label) {
		ponteiroAreaDeDados++;
		reservasDeMemoria.add(label);
		return ponteiroAreaDeDados;
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

	public void geraArquivoMVN() {
		codigoMVN.append(geraCodigoAreaDeDados());
		String finalCode = codigoMVN.toString();
		Util.Log("Código final Gerado:\n"+ finalCode);
	}

	private String geraCodigoAreaDeDados() {
		StringBuffer code = new StringBuffer();
		code.append("\n&/");
		code.append(inicioAreaDeDados.toString()+"\n");
		
		for (int i=0;i<reservasDeMemoria.tamanho;i++){
			code.append((String)reservasDeMemoria.get(i)+" K /0000\n");
		}
		return code.toString();
	}

}
