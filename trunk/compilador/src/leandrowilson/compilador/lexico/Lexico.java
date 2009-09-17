package leandrowilson.compilador.lexico;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import leandrowilson.compilador.lexico.*;

public class Lexico {
	private final int ESTADO_INICIAL = 0;
	private final int ESTADO_GERATOKEM_ID = 1;
	private final int ESTADO_GERATOKEM_ATRIB = 2;
	private final int ESTADO_GERATOKEM_OPERADOR = 3;
    private final int MAXBUFFER = 4096;
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
		bufferDeLeitura1 = new char[MAXBUFFER];
		bufferDeLeitura2 = new char[MAXBUFFER];
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
		tokenBuffer[tokenBufferPointer]= ch;
		
		while (!fimDeArquivo(ch)){
			estadoAtual = tabelaDeTransicao.proximoEstado(estadoAtual, ch);
			if (estadoFinal(estadoAtual)){
				executaAcaoDeEstadoFinal(estadoAtual);	
			}
			ch = leProximoCaracter();
		}
		
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

	private boolean estadoFinal(int estadoAtual2) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean fimDeArquivo(int ch) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Este metodo le o arquivo inicializa corretamente o buffer
	 * @param nomeDoArquivo2
	 */
	private void leArquivo(String nomeDoArquivo) {
		    arquivoLido = new File("C:\\test.txt");
		    FileInputStream fis = null;
		    BufferedInputStream bis = null;
		    DataInputStream dis = null;

		    try {
			    if (!file.canRead()){
			    	System.out.println("Arquivo não disponível para leitura");
			    }
			    else
			    {
			      fis = new FileInputStream(file);
	
			      // Here BufferedInputStream is added for fast reading.
			      bis = new BufferedInputStream(fis);
			      dis = new DataInputStream(bis);
	
			      // dis.available() returns 0 if the file does not have more lines.
			      while (dis.available() != 0) {
			      // this statement reads the line from the file and print it to
			        // the console.
			        System.out.println(dis.readChar());
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
		if (bufferEmUso ==1){
			posicaoDoBuffer++;
			char caracterLido = bufferDeLeitura1[posicaoDoBuffer]; 
			if (chegouNoFimDoBuffer()){
				atualizaBuffer2();
				bufferEmUso = 2 ;
				posicaoDoBuffer = -1;
			}
			return caracterLido;
		}
		else {
			posicaoDoBuffer++;
			char caracterLido = bufferDeLeitura2[posicaoDoBuffer];
			if (chegouNoFimDoBuffer()){
				atualizaBuffer1();
				bufferEmUso = 1 ;
				posicaoDoBuffer = -1;
			}
			return caracterLido;
		}
		
	}

	private void atualizaBuffer1() {
		// TODO Auto-generated method stub
		
	}

	private void atualizaBuffer2() {
		// TODO Auto-generated method stub
		
	}

	private boolean chegouNoFimDoBuffer() {
		// TODO Auto-generated method stub
		return false;
	}

}
