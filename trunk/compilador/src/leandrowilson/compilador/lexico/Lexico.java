package leandrowilson.compilador.lexico;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



/**
 * Esta classe faz a analise lexica do codigo
 * @author Leandro Cordeiro David(leandrodvd@gmail.com) e Wilson Faria(wilsonfaria86@gmail.com)
 */
public class Lexico {
	
    private final int TAMANHOBUFFER = 4096;
    //private final int MAX_TAMANHO_TOKEN = 1024;
	private int estadoAtual ;
	private char[] bufferDeLeitura1;
	private char[] bufferDeLeitura2;
	private int bufferEmUso;
	private int posicaoDoBuffer;
	private TabelaDeTransicao tabelaDeTransicao;
	private List listaDeTokens = new List();
	//private char[] tokenBuffer; //buffer para ir armazenando caracter a cartecer lido. É resetado quando um token é gerado(volta pro estado 0)
	private StringBuffer tokenBuffer;
	//private int tokenBufferPointer; // Ponteiro para o tokenBuffer
	private File arquivoLido ; //Arquivo
	FileInputStream fis =null ; //FileInputStream
	BufferedInputStream bis =null; //BufferedInputStream
	DataInputStream dis =null; //DataInputStream
	private int linha = 0; //contagem de linhas do arquivo
	
	public Lexico() {
		tabelaDeTransicao= new TabelaDeTransicao();
		bufferDeLeitura1 = new char[TAMANHOBUFFER];
		bufferDeLeitura2 = new char[TAMANHOBUFFER];
		estadoAtual = TabelaDeTransicao.ESTADO_INICIAL;
		posicaoDoBuffer = -1;
		//tokenBuffer = new char[MAX_TAMANHO_TOKEN];
		tokenBuffer =new StringBuffer();
		//tokenBufferPointer = -1;
		
	}
	
	public List obterListaDeTokens(String nomeDoArquivo){
		leArquivo(nomeDoArquivo);
		analisaArquivo();
		return listaDeTokens;
	}
	
	/**
	 * Analisa o arquivo e gera os tokens - Esse metodo faz o grossodo lexico
	 * ELE RODA A MAQUINA DE AUTOMATO< FICA LENDO A TABELA DE TRANSICAO E TAL
	 * 
	 */
	private void analisaArquivo() {
		char ch = leProximoCaracter();
		System.out.println("Caracter lido:'"+ch+"'-"+(int)ch);
		//tokenBufferPointer++;
		//tokenBuffer[tokenBufferPointer]= ch;
		
		while (!fimDoArquivo(ch)){
			if (descartarCaracter(ch)){
				ch = leProximoCaracter();
			}
			else{
				tokenBuffer.append(ch);
				estadoAtual = tabelaDeTransicao.proximoEstado(estadoAtual, ch);
				if (tabelaDeTransicao.estadoFinal(estadoAtual)){
					executaAcaoDeEstadoFinal(estadoAtual);
				}
				
				ch = leProximoCaracter();
				System.out.println("Caracter lido:'"+ch+"'-"+(int)ch);
				
			}
		}
		if(!(tabelaDeTransicao.estadoFinal(estadoAtual)||tabelaDeTransicao.estadoInicial(estadoAtual))){
			//Acabou de ler o arquivo mas o ultimo caracter lido não levou a um estado final
			//Fecha um token quebrado - O analisador sintatico se vira para trata-lo
			fechaTokenQuebrado();
		}
	}
	private boolean descartarCaracter(char ch) {
		//Descarta espaço só se estiver no estado inicial
		if(tabelaDeTransicao.estadoInicial(estadoAtual) && (int)ch==32){ 
			return true;
		}
		//Descarta caracter de line feed
		if((int)ch == 10){
			return true;
		}
		//Descarta caracter de carriage return
		if((int)ch == 13){
			return true;
		}
		return false;
	}

	private void fechaTokenQuebrado() {
		Token token = new Token(tokenBuffer.toString(),obterTipoParaEstado(estadoAtual));
		adicionaTokenNaLista(token);
		tokenBuffer = new StringBuffer();
		//tokenBuffer = new char[MAX_TAMANHO_TOKEN];
	}

	private void adicionaTokenNaLista(Token token) {
		//System.out.println("Adicionando Token na Lista-Tipo:"+token.tipo+" Valor:"+String.valueOf(token.valor));
		this.listaDeTokens.add(token);
	}

	/**
	 * Verifica a qual automato um estado pertence</br>
	 * usado para quando precisar gerar um token sem ter atingido um estado final
	 * @param estado
	 * @return int Tipo
	 */
	private TipoToken obterTipoParaEstado(int estado) {
		// TODO Auto-generated method stub
		return TipoToken.ID;
	}

	private void executaAcaoDeEstadoFinal(int estado) {
		switch(estado)
		{	
			case TabelaDeTransicao.ESTADO_GERATOKEM_ID:
				retornaCaracter();
				adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.ID));
				limpaTokenBuffer();
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				break;
			case TabelaDeTransicao.ESTADO_GERATOKEM_OPERADOR:
				adicionaTokenNaLista(new Token(tokenBuffer.toString()));
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				limpaTokenBuffer();
				break;
			case TabelaDeTransicao.ESTADO_GERATOKEM_OPERADOR_RET:
				retornaCaracter();
				adicionaTokenNaLista(new Token(tokenBuffer.toString()));
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				limpaTokenBuffer();
				break;
			case TabelaDeTransicao.ESTADO_GERATOKEM_NUMERO:
				retornaCaracter();
				adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.NUMERO));
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				limpaTokenBuffer();
				break;
			case TabelaDeTransicao.ESTADO_GERATOKEM_STRING:
				adicionaTokenNaLista(new Token(tokenBuffer.toString(), TipoToken.STRING));
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				limpaTokenBuffer();
				break;
			case TabelaDeTransicao.ESTADO_ERRO:
				fechaTokenQuebrado();
				estadoAtual=TabelaDeTransicao.ESTADO_INICIAL;
				limpaTokenBuffer();
				break;
				//demais cases de estado final
		}
		
	}

	
	private void retornaCaracter() {
		posicaoDoBuffer--;
		tokenBuffer.deleteCharAt(tokenBuffer.length()-1);
	}

	private void limpaTokenBuffer() {
		tokenBuffer = new StringBuffer();
		//tokenBufferPointer=-1;
		
	}

	/**
	 * Este metodo le o arquivo inicializa corretamente o buffer
	 * @param nomeDoArquivo2
	 */
	private void leArquivo(String nomeDoArquivo) {
		    arquivoLido = new File(nomeDoArquivo);
		    System.out.println("Lendo arquivo:"+nomeDoArquivo);
		    fis = null;
		    bis = null;
		    dis = null;

		    try {
			    if (!arquivoLido.canRead()){
			    	System.out.println("Arquivo não disponível para leitura");
			    }
			    else
			    {
			      fis = new FileInputStream(arquivoLido);
	
			      // Here BufferedInputStream is added for fast reading.
			      bis = new BufferedInputStream(fis);
			      dis = new DataInputStream(bis);
			      // dis.available() returns 0 if the file does not have more lines.
			      if (dis.available()!= 0) {
			    	  carregaBuffer1();
			      }
			      else{
			    	  System.out.println("ERRO- Arquivo em branco");
			      }
	
			      // dispose all the resources after using them.
			      //fis.close();
			      //bis.close();
			      //dis.close();
			    }
		    } catch (FileNotFoundException e) {
		    	System.out.println("Arquivo não encontrado");
		    	
		      e.printStackTrace();
		    } catch (IOException e) {
		    	System.out.println("Erro de E/S");
		      e.printStackTrace();
		    }		
	}

	
	/**
	 * @param args
	 */
	public  char leProximoCaracter() {
		
		return leProximoCaracterDoBuffer();

	}

	private char leProximoCaracterDoBuffer() {
		posicaoDoBuffer++;
		if (bufferEmUso ==1){
			char caracterLido = bufferDeLeitura1[posicaoDoBuffer]; 
			if (chegouNoFimDoBuffer()){
				carregaBuffer2();
			}
			return caracterLido;
		}
		else {
			char caracterLido = bufferDeLeitura2[posicaoDoBuffer];
			if (chegouNoFimDoBuffer()){
				carregaBuffer1();
			}
			return caracterLido;
		}
		
	}

	private void carregaBuffer1() {
		atualizaBuffer1();
		bufferEmUso = 1 ;
		posicaoDoBuffer = -1;
	}

	private void carregaBuffer2() {
		atualizaBuffer2();
		bufferEmUso = 2 ;
		posicaoDoBuffer = -1;
	}

	private void atualizaBuffer1() {
		
		try {
			for(int i =0 ;i<TAMANHOBUFFER ;i++){
				if( dis.available()!= 0){
					bufferDeLeitura1[i]=(char) dis.read();
				}
				else{
					bufferDeLeitura1[i]=(char) 0;
					break;
				}
				
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura de caracter do arquivo");
			e.printStackTrace();
		}
		
	}

	private boolean fimDoArquivo(char ch) {
		return (0==(int)ch);
	}

	private void atualizaBuffer2() {
		try {
			for(int i =0 ;i<TAMANHOBUFFER ;i++){
				if( dis.available()!= 0){
					bufferDeLeitura2[i]=(char) dis.read();
				}
				else{
					bufferDeLeitura2[i]=(char) 0;
				}
				
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura de caracter do arquivo");
			e.printStackTrace();
		}
		
	}

	private boolean chegouNoFimDoBuffer() {
		return posicaoDoBuffer==TAMANHOBUFFER-1;
	}

}
