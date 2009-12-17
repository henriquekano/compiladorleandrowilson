package leandrowilson.compilador;

import java.math.BigInteger;
import java.util.Stack;

import javax.swing.text.Position;

public class Semantico {
	Integer contadorEscopo =0;
	List erros = new List();
	StringBuffer codigoMVN = new StringBuffer();
	Integer inicioAreaDeDados = 1000;
	Integer ponteiroAreaDeDados = inicioAreaDeDados-1;
	List reservasDeMemoria = new List();
	List listaDeConstantes = new List();
	List listaDeValoresDeConstantes = new List();
	Stack<String> pilhaCodigo = new Stack<String>();
	Integer tempCounter =0;
	private boolean calculouExpBooleana;
	private String label_terminaWhile;
	private String label_While;
	private static String label_Else = null;
	
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
					analisaToken(pilhaSemantico.pop_Token(),TipoToken.CHAVE_ABRE);
					analisaToken(pilhaSemantico.pop_Token(),TipoToken.PR_MAIN);
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
						erroSemantico_redeclaracaoDeVariavel(token);
					}
					else{
						Token t;
						List indices = new List();
						t = pilhaSemantico.pop_Token();
						while (!t.tipo.ehTipoPrimitivo()){
							analisaToken(t, TipoToken.NUMERO);
							indices.add(new Integer(t.valor));
							t = pilhaSemantico.pop_Token();
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
				List indices=null;
				String label_continue = null;
				switch (transicaoSemantica) {
				case 0://(0, "identificador") -> 1
					String chave = token.valor;
					Descritor d = escopo.busca(chave);
					if (d ==null){
						erros.add(new Erro(TipoErro.SEMANTICO_VARIAVEL_NAO_DECLARADA,token));
					}
					else{
						pilhaSemantico.push(d);
						indices = new List();
						pilhaSemantico.push(indices);
						elSemantico.pilhaSemantico = pilhaSemantico;
					}
					break;
				case 1://(0, "if") -> 2
					calculouExpBooleana = false;
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 2://(0, "while") -> 3
					calculouExpBooleana = false;
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 3://(0, "input") -> 4
					break;
				case 4://(0, "output") -> 4
					break;
				case 5://(0, "callproc") -> 5
					break;
				case 6://(1, "[") -> 6
					break;
				case 7://(1, "=") -> 7
					indices = pilhaSemantico.pop_List();
					Descritor left = pilhaSemantico.peek_Descritor();
					String label =null;
					if (indices.tamanho >0){
//						Integer pos = left.GetPosicao(indices.toIntArray());
						Integer pos = left.GetIndice(indices.toIntArray());
						label = left.label+"_"+pos.toString();
						indices = new List();
					}
					else{
						label = left.label;
					}
					pilhaSemantico.push(token);
					pilhaCodigo.push("    MM "+ label + "\n");
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 8://(2, "(") -> 17
					break;
				case 9://(3, "(") -> 26
					break;
				case 10://(4, "identificador") -> 25
					break;
				case 11://(5, "identificador") -> 8					
					break;
				case 12://(6, "identificador") -> 15
					break;
				case 13://(6, "inteiro") -> 15
					indices = pilhaSemantico.pop_List();
					indices.add(new Integer(token.valor));
					pilhaSemantico.push(indices);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 14://(7, expressao) -> 9
					break;
				case 15://(8, "(") -> 10
					break;
				case 16://(9, ";") -> 11
					indices = pilhaSemantico.pop_List();
					Descritor desc = pilhaSemantico.pop_Descritor();
					switch (desc.tipo) {
					case VAL_INT:
						Token t = pilhaSemantico.pop_Token();
						//t = pilhaSemantico.pop_Token(); //desempilha sinal de + ou -
						//TODO tratar sinal de + ou -
						analisaToken(t, TipoToken.ATRIB);
						left=pilhaSemantico.pop_Descritor();
						if (left.tipo.equals(TipoDescritor.VAR_INT) ||left.tipo.equals(TipoDescritor.VVAR_INT) ){
							//gera Codigo LV TMP1 MM leftDesc
							codigoMVN.append("  LV "+desc.label+"\n");
							codigoMVN.append(pilhaCodigo.pop());
						}
						else{
							erros.add(new Erro(TipoErro.SEMANTICO_TIPOS_DE_DADOS_INCOMPATIVEIS,t));
						}
						break;
					case VAL_BOOL:
						break;
					case VAL_STRING:
						break;
					case VVAR_BOOL:
						break;
					case VVAR_INT:
						break;
					case VVAR_STRING:
						break;
					case VAR_BOOL:
						break;
					case VAR_INT:
						t = pilhaSemantico.pop_Token();
						//t = pilhaSemantico.pop_Token(); //desempilha sinal de + ou -
						//TODO tratar sinal de + ou -
						analisaToken(t, TipoToken.ATRIB);
						left=pilhaSemantico.pop_Descritor();
						if (left.tipo.equals(TipoDescritor.VAR_INT) ||left.tipo.equals(TipoDescritor.VVAR_INT) ){
							//gera Codigo LV TMP1 MM leftDesc
							codigoMVN.append("  LV "+desc.label+"\n");
							codigoMVN.append(pilhaCodigo.pop());
						}
						else{
							erros.add(new Erro(TipoErro.SEMANTICO_TIPOS_DE_DADOS_INCOMPATIVEIS,t));
						}
						break;
					case VAR_STRING:
						break;
					default:
						break;
					}
					break;
				case 17://(10, "identificador") -> 12
					break;
				case 18://(10, ")") -> 9
					break;
				case 19://(10, "numero") -> 13
					break;
				case 20://(10, "string") -> 13
					break;
				case 21://(12, "[") -> 16
					break;
				case 22://(12, ")") -> 9
					break;
				case 23://(12, ",") -> 14
					break;
				case 24://(13, ")") -> 9
					break;
				case 25://(13, ",") -> 14
					break;
				case 26://(14, "identificador") -> 12
					break;
				case 27://(14, "numero") -> 13
					break;
				case 28://(14, "string") -> 13
					break;
				case 29://(15, "]") -> 1
					break;
				case 30://(16, "identificador") -> 18
					break;
				case 31://(16, "inteiro") -> 18
					break;
				case 32://(17, expbooleana) -> 19
					if(calculouExpBooleana){
						pilhaSemantico.pop_List();
						d = pilhaSemantico.pop_Descritor();
						d.AtualizaLabel(geraLabel_Temp());
						switch (d.tipo) {
							case VAL_INT:
								break;
							case VAL_BOOL:
								String lbl = d.GetLabel();
								geraCodigoInicioIf(lbl);
								break;
							case VAL_STRING:
								break;
							case VVAR_BOOL:
								break;
							case VVAR_INT:
								break;
							case VVAR_STRING:
								break;
							case VAR_BOOL:			
								break;
							case VAR_INT:
								break;
							case VAR_STRING:
								break;
							default:
								break;
						}
					}
					break;
				case 33://(18, "]") -> 12
					break;
				case 34://(19, ")") -> 20
					break;
				case 35://(20, "{") -> 21
					pilhaSemantico.push(token);
					contadorEscopo++;
					escopo = new Escopo(escopo, contadorEscopo);
					elSemantico.pilhaSemantico = pilhaSemantico;
					elSemantico.escopo = escopo;
					break;
				case 36://(21, declaracao) -> 21
					break;
				case 37://(21, comando) -> 21
					break;
				case 38://(21, "}") -> 22
					escopo = escopo.escopoPai;
					analisaToken(pilhaSemantico.pop_Token(),TipoToken.CHAVE_ABRE);
					analisaToken(pilhaSemantico.pop_Token(),TipoToken.PR_IF);
					label_continue = geraLabel_Temp();
					geraCodigoFimIf(label_continue);
					break;
				case 39://(22, "else") -> 23
					pilhaSemantico.push(token);					
					break;
				case 40://(23, "{") -> 24
					pilhaSemantico.push(token);
					contadorEscopo++;
					escopo = new Escopo(escopo, contadorEscopo);
					elSemantico.pilhaSemantico = pilhaSemantico;
					elSemantico.escopo = escopo;
					break;
				case 41://(24, declaracao) -> 24
					break;
				case 42://(24, comando) -> 24
					break;
				case 43://(24, "}") -> 11
					escopo = escopo.escopoPai;
					analisaToken(pilhaSemantico.pop_Token(),TipoToken.CHAVE_ABRE);
					Token t = pilhaSemantico.pop_Token();
					if(t.tipo == TipoToken.PR_ELSE){
						geraCodigoFimElse(label_continue);
					}
					else if(t.tipo == TipoToken.PR_WHILE){
						geraCodigoFimWhile(label_terminaWhile);
					}
					else{
						analisaToken(t,TipoToken.PR_ELSE);
						analisaToken(t,TipoToken.PR_WHILE);
					}
					
					break;
				case 44://(25, "[") -> 27
					break;
				case 45://(25, ";") -> 11
					break;
				case 46://(26, expbooleana) -> 28
					if(calculouExpBooleana){
						pilhaSemantico.pop_List();
						d = pilhaSemantico.pop_Descritor();
						d.AtualizaLabel(geraLabel_Temp());
						switch (d.tipo) {
							case VAL_INT:
								break;
							case VAL_BOOL:
								String lbl = d.GetLabel();
								geraCodigoInicioWhile(lbl);
								break;
							case VAL_STRING:
								break;
							case VVAR_BOOL:
								break;
							case VVAR_INT:
								break;
							case VVAR_STRING:
								break;
							case VAR_BOOL:			
								break;
							case VAR_INT:
								break;
							case VAR_STRING:
								break;
							default:
								break;
						}
					}
					break;
				case 47://(27, "identificador") -> 29
					break;
				case 48://(27, "inteiro") -> 29
					break;
				case 49://(28, ")") -> 23
					break;
				case 50://(29, "]") -> 25
					break;
				default:
					break;
				}
				break;
			case EXPRESSAO:
				switch (transicaoSemantica) {
					case 0://(0, expbooleana) -> 1
						break;
					case 1://(0, exparitmetica) -> 1
						break;
					case 2://(0, expstring) -> 1
						break;
					case 3://(0, exprelacional) -> 1
						break;
					case 4://(0, "callfunc") -> 2
						break;
					case 5://(2, "identificador") -> 3
						break;
					case 6://(3, "(") -> 4
						break;
					case 7://(4, "identificador") -> 5
						break;
					case 8://(4, "numero") -> 6
						break;
					case 9://(4, "string") -> 6
						break;
					case 10://(4, ")") -> 7
						break;
					case 11://(5, "[") -> 8
						break;
					case 12://(5, ",") -> 9
						break;
					case 13://(5, ")") -> 7
						break;
					case 14://(6, ",") -> 9
						break;
					case 15://(6, ")") -> 7
						break;
					case 16://(7, ";") -> 1
						break;
					case 17://(8, "identificador") -> 10
						break;
					case 18://(8, "inteiro") -> 10
						break;
					case 19://(9, "identificador") -> 5
						break;
					case 20://(9, "numero") -> 6
						break;
					case 21://(9, "string") -> 6
						break;
					case 22://(10, "]") -> 5
						break;
					default:
						break;
				}
				break;
			case EXPBOOLEANA:
				calculouExpBooleana = true;
				switch (transicaoSemantica) {
				case 0://(0, "identificador") -> 1
					String chave = token.valor;
					Descritor d = escopo.busca(chave);
					Boolean end = false;
					if (d ==null){
						erros.add(new Erro(TipoErro.SEMANTICO_VARIAVEL_NAO_DECLARADA,token));
					}
					else{
						Token t = pilhaSemantico.pop_Token();
						if (t.tipo.equals(TipoToken.EQUAL_COMPARISON)) {
								indices = pilhaSemantico.pop_List(); //joga fora
								Descritor fakeDesc = pilhaSemantico.pop_Descritor();
								codigoMVN.append("   - " + d.label);
								String tmpLabel = geraLabel_Temp();
								codigoMVN.append("   MM " + tmpLabel);
								d= new Descritor(tmpLabel,TipoDescritor.VAL_BOOL);
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
								end=true;
						}
						else{
							pilhaSemantico.push(t);
								codigoMVN.append("   LV " + d.label);
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
						}
						if(end){
							pilhaSemantico.pop_List();
							pilhaSemantico.pop_Descritor();
						}
						elSemantico.pilhaSemantico = pilhaSemantico;
					}
					break;
				case 1://(0, "true") -> 2
					break;
				case 2://(0, "false") -> 2
					break;
				case 3://(0, "(") -> 3
					break;
				case 4://(0, "!") -> 4
					break;
				case 5://(1, "[") -> 5
					break;
				case 6://(1, "|") -> 0
					break;
				case 7://(1, "&") -> 0
					break;
				case 8://(1, "!=") -> 0
					//pilhaSemantico.push(token);
					//elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 9://(1, "==") -> 0
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					
					break;
				case 10://(2, "|") -> 0
					break;
				case 11://(2, "&") -> 0
					break;
				case 12://(2, "!=") -> 0
					break;
				case 13://(2, "==") -> 0
					break;
				case 14://(3, expbooleana) -> 6
					break;
				case 15://(4, "(") -> 3
					break;
				case 16://(5, "identificador") -> 7
					break;
				case 17://(5, "inteiro") -> 7
					break;
				case 18://(6, ")") -> 2
					break;
				case 19://(7, "]") -> 1
					break;
				default:
					break;
				}
				break;
			case EXPRELACIONAL:
				switch (transicaoSemantica) {
				case 0://(0, "identificador") -> 1
					String chave = token.valor;
					Descritor d = escopo.busca(chave);
					if (d ==null){
						erros.add(new Erro(TipoErro.SEMANTICO_VARIAVEL_NAO_DECLARADA,token));
					}
					else{
						pilhaSemantico.push(d);
						indices = new List();
						pilhaSemantico.push(indices);
						elSemantico.pilhaSemantico = pilhaSemantico;
					}
					break;
				case 1://(0, "numero") -> 2
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 2://(1, "[") -> 3
					break;
				case 3://(1, "<") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 4://(1, ">") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 5://(1, "<=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 6://(1, ">=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 7://(1, "==") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 8://(1, "!=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 9://(2, "<") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 10://(2, ">") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 11://(2, "<=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 12://(2, ">=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 13://(2, "==") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 14://(2, "!=") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 15://(3, "identificador") -> 7
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 16://(3, "inteiro") -> 7
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 17://(4, "identificador") -> 5
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 18://(4, "numero") -> 6
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 19://(5, "[") -> 8
					if(!token.tipo.equals(TipoToken.COLCHETE_ABRE)){
						//ACABOU!
					}
					break;
				case 20://(7, "]") -> 1
					break;
				case 21://(8, "identificador") -> 9
//					pilhaSemantico.push(token);
//					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 22://(8, "inteiro") -> 9
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 23://(9, "]") -> 5
					break;
				default:
					break;
				}
				break;
			case EXPARITMETICA:
				indices = new List();
				switch (transicaoSemantica) {
				case 0://(0, "-") -> 1
//					pilhaSemantico.push(token);
//					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 1://(0, "+") -> 1
//					pilhaSemantico.push(token);
//					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 2://(1, "identificador") -> 2
					String chave = token.valor;
					Descritor d = escopo.busca(chave);
					if (d ==null){
						erros.add(new Erro(TipoErro.SEMANTICO_VARIAVEL_NAO_DECLARADA,token));
					}
					else{
						Token t = pilhaSemantico.pop_Token();
						if (t.tipo.equals(TipoToken.PLUS)) {
							if (d.tipo.equals(TipoDescritor.VAR_INT)){
								indices = pilhaSemantico.pop_List(); //joga fora
								Descritor fakeDesc = pilhaSemantico.pop_Descritor();
								codigoMVN.append("   + " + d.label);
								String tmpLabel = geraLabel_Temp();
								codigoMVN.append("   MM " + tmpLabel);
								d= new Descritor(tmpLabel,TipoDescritor.VAL_INT);
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
							}
							else{
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
							}
						}
						else{
							pilhaSemantico.push(t);
							if (d.tipo.equals(TipoDescritor.VAR_INT)){
								codigoMVN.append("   LV " + d.label);
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
							}
							else{
								indices = new List();
								pilhaSemantico.push(d);
								pilhaSemantico.push(indices);
							}
							
						}
						elSemantico.pilhaSemantico = pilhaSemantico;
						
					}
					break;
				case 3://(1, "numero") -> 3
					chave = geraLabel_Const(new Integer(token.valor));
					d = new Descritor(chave, TipoDescritor.VAL_INT);
					pilhaSemantico.push(d);
					indices = new List();
					pilhaSemantico.push(indices);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 4://(1, "(") -> 4
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
					
				//ACABA NO DOIS!!!!!
				case 5://(2, "-") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 6://(2, "+") -> 1
//					d = pilhaSemantico.pop_Descritor();
//					String label;
//					if(indices.tamanho>0){
//						Integer pos = d.GetPosicao(indices.toIntArray());
//						label = d.label+"_"+pos.toString();
//					}
//					else{
//						label = d.label;
//					}
//					
//					codigoMVN.append("   LV  "+d.label);
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 7://(2, "[") -> 6
					break;
				case 8://(2, "/") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 9://(2, "*") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 10://(3, "-") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 11://(3, "+") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 12://(3, "/") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 13://(3, "*") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 14://(4, exparitmetica) -> 5
					break;
				case 15://(5, ")") -> 3
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 16://(6, "identificador") -> 7
					break;
				case 17://(6, "inteiro") -> 7
					indices=pilhaSemantico.pop_List();
					indices.add(new Integer(token.valor));
					d=pilhaSemantico.pop_Descritor();
					String label;
					if (indices.tamanho == d.indicesMaximosTam){
						Token tok = pilhaSemantico.pop_Token();
						if (tok.tipo.equals(TipoToken.PLUS)){
							Integer pos = d.GetIndice(indices.toIntArray());
							label = d.label+"_"+pos.toString();
							codigoMVN.append("   + " + d.label);
							String tmpLabel = geraLabel_Temp();
							codigoMVN.append("   MM " + tmpLabel);
							d= new Descritor(tmpLabel,TipoDescritor.VAL_INT);
							indices = new List();
							pilhaSemantico.push(d);
							pilhaSemantico.push(indices);
							
						}
						else{
							pilhaSemantico.push(tok);
							Integer pos = d.GetIndice(indices.toIntArray());
							label = d.label+"_"+pos.toString();
							codigoMVN.append("   LV " + d.label);
							indices = new List();
							pilhaSemantico.push(d);
							pilhaSemantico.push(indices);
							
							
						}
					}
					else{
						pilhaSemantico.push(d);
						pilhaSemantico.push(indices);
					}
					break;
				case 18://(7, "]") -> 2
					break;
				default:
					break;
				}
				break;
				
			case EXPSTRING:
				switch (transicaoSemantica) {
				case 0://(0, "tipo_string") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 1://(1, "+") -> 2
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					break;
				case 2://(2, "tipo_string") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					//ACABOU!!!
					break;
				case 3://(2, "identificador") -> 1
					pilhaSemantico.push(token);
					elSemantico.pilhaSemantico = pilhaSemantico;
					//ACABOU!!!
					break;
				default:
					break;
				}
				break;
		}
		
		return elSemantico;
	}

	private String geraLabel_Const(Integer valor) {
		ponteiroAreaDeDados++;
		String lbl = "COSNT"+tempCounter.toString();
		tempCounter++;
		listaDeConstantes.add(lbl);
		listaDeValoresDeConstantes.add(valor);
		return lbl;
	}

	private String geraLabel_Temp() {
		ponteiroAreaDeDados++;
		String lbl = "TMP"+tempCounter.toString();
		tempCounter++;
		reservasDeMemoria.add(lbl);
		return lbl;
	}


	private void erroSemantico_redeclaracaoDeVariavel(Token token) {
		erros.add(new Erro(TipoErro.SEMANTICO_REDECALRACAO_DE_VARIAVEL,token));
		Util.Log("ErroSemantico-RedeclaracaoDe Variavel");
		
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

	private void analisaToken(Token lido, TipoToken esperado) {
		if (!lido.tipo.equals(esperado)){
			erroSemantico(lido);
		}
	}

	private void erroSemantico(Token token) {
		erros.add(new Erro(TipoErro.SEMANTICO,token));
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
		for (int i=0;i<listaDeConstantes.tamanho;i++){
			code.append((String)listaDeConstantes.get(i)+" K /"+completaZeros((Integer)listaDeValoresDeConstantes.get(i))+"\n");
		}
		return code.toString();
	}
	



	private String completaZeros(Integer valor) {
		String str = Integer.toHexString(valor);
		int tamanho =str.length();
		if (tamanho<4){
			int falta = 4-tamanho;
			for (int i=0;i<falta;i++){
				str = "0"+str;
			}
		}
		return str;
	}



	private void geraCodigoInicioIf(String labelParam) {
		StringBuffer code = new StringBuffer();
		String label_IF = geraLabel_Temp();
		label_Else = geraLabel_Temp();
		code.append("LV " + labelParam+"\n");
		code.append("JZ " + label_IF+"\n");
		code.append("JP " + label_Else+"\n");
		code.append(label_IF + " ");
		
	}
	private void geraCodigoFimIf(String label_continue) {
		StringBuffer code = new StringBuffer();
		code.append("JP " + label_continue);
		code.append(label_Else + " ");
	}
	private void geraCodigoFimElse(String label_continue){
		StringBuffer code = new StringBuffer();
		code.append(label_continue + " ");
	}
	
	private void geraCodigoInicioWhile(String labelParam) {
		StringBuffer code = new StringBuffer();
		label_While = geraLabel_Temp();
		String label_percorreWhile = geraLabel_Temp();
		label_terminaWhile = geraLabel_Temp();
		
		code.append(label_While + " LV " + labelParam+"\n");
		code.append("JZ " + label_percorreWhile+"\n");
		code.append("JP " + label_terminaWhile+"\n");
		code.append(label_percorreWhile + " ");
	}
	private void geraCodigoFimWhile(String label_continue) {
		StringBuffer code = new StringBuffer();
		code.append("JP " + label_While+"\n");
		code.append(label_terminaWhile + " ");
	}
	
	
	private int boolToInt(Boolean bool){
		int b_int = 0;
		if(bool == false) b_int = 1;
		return b_int;
	}
	


}
