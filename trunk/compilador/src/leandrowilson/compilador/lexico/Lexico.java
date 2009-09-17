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
	private final int ESTADO_INICIAL = 0;
	private final int ESTADO_GERATOKEM_ID = 1;
	private final int ESTADO_GERATOKEM_ATRIB = 2;
	private final int ESTADO_GERATOKEM_OPERADOR = 3;
    private final int TAMANHOBUFFER = 4096;
    private final int MAX_TAMANHO_TOKEN = 1024;
	private int estadoAtual ;
	private char[] bufferDeLeitura1;
	private char[] bufferDeLeitura2;
	private int bufferEmUso;
	private int posicaoDoBuffer;
	private TabelaDeTransicao tabelaDeTransicao;
	private String nomeDoArquivo;
	private List listaDeTokens;
	private char[] tokenBuffer; //buffer para ir armazenando caracter a cartecer lido. É resetado quando um token é gerado(volta pro estado 0)
	private int tokenBufferPointer; // Ponteiro para o tokenBuffer
	private File arquivoLido ; //Arquivo
	FileInputStream fis =null ; //FileInputStream
	BufferedInputStream bis =null; //BufferedInputStream
	DataInputStream dis =null; //DataInputStream
	
	public Lexico() {
		tabelaDeTransicao= new TabelaDeTransicao();
		estadoAtual = 0;
		bufferDeLeitura1 = new char[TAMANHOBUFFER];
		bufferDeLeitura2 = new char[TAMANHOBUFFER];
		posicaoDoBuffer = -1;
		tokenBuffer = new char[MAX_TAMANHO_TOKEN];
		tokenBufferPointer = 0;
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
		tokenBuffer[tokenBufferPointer++]= ch;
		
		
		while (!acabouArquivo()){
			estadoAtual = tabelaDeTransicao.proximoEstado(estadoAtual, ch);
			if (tabelaDeTransicao.estadoFinal(estadoAtual)){
				executaAcaoDeEstadoFinal(estadoAtual);	
			}
			ch = leProximoCaracter();
			tokenBufferPointer++;
			if (tokenBufferPointer<MAX_TAMANHO_TOKEN){
				tokenBuffer[tokenBufferPointer++]= ch;
			}
			else{
				System.out.println("ERRO-Tamanho máximo de token ("+MAX_TAMANHO_TOKEN+") atingido");
				fechaTokenQuebrado();
				tokenBuffer[tokenBufferPointer++]= ch;
			}
		}
		if(!tabelaDeTransicao.estadoFinal(estadoAtual)){
			//Acabou de ler o arquivo mas o ultimo caracter lido não levou a um estado final
			//Fecha um token quebrado - O analisador sintatico se vira para trata-lo
			fechaTokenQuebrado();
		}
	}
	private void fechaTokenQuebrado() {
		Token token = new Token(tokenBuffer,obterTipoParaEstado(estadoAtual));
		adicionaTokenNaLista(token);
	}

	private void adicionaTokenNaLista(Token token) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Verifica a qual automato um estado pertence</br>
	 * usado para quando precisar gerar um token sem ter atingido um estado final
	 * @param estado
	 * @return int Tipo
	 */
	private TipoToken obterTipoParaEstado(int estado) {
		// TODO Auto-generated method stub
		return TipoToken.ATRIB;
	}

	private void executaAcaoDeEstadoFinal(int estado) {
		switch(estado)
		{	
			case ESTADO_GERATOKEM_ID:
				// Codigo para gerar o tokem
				break;
			case ESTADO_GERATOKEM_ATRIB:
				//codigo para gerar o token
				break;
				//demais cases de estado final
		}
		
	}

	
	/**
	 * Este metodo le o arquivo inicializa corretamente o buffer
	 * @param nomeDoArquivo2
	 */
	private void leArquivo(String nomeDoArquivo) {
		    arquivoLido = new File("C:\\test.txt");
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
			      if (!acabouArquivo()) {
			    	  carregaBuffer1();
			      }
			      else{
			    	  System.out.println("ERRO- Arquivo em branco");
			      }
	
			      // dispose all the resources after using them.
			      fis.close();
			      bis.close();
			      dis.close();
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
				if( !acabouArquivo()){
					bufferDeLeitura1[i]=(char) dis.read();
				}
				
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura de caracter do arquivo");
			e.printStackTrace();
		}
		
	}

	private boolean acabouArquivo() {
		boolean retorno=false;
		
		try {
			retorno = dis.available()== 0 ;
		} catch (IOException e) {
			System.out.println("erro na leitura de caracter do arquivo");
			e.printStackTrace();
		}
		return retorno;
	}

	private void atualizaBuffer2() {
		try {
			for(int i =0 ;i<TAMANHOBUFFER ;i++){
				if( !acabouArquivo()){
					bufferDeLeitura2[i]=(char) dis.read();
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
