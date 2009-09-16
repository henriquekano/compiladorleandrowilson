package leandrowilson.compilador.lexico;

import leandrowilson.compilador.lexico.PalavraReservada;

public class Lexico {
    private final int MAXBUFFER = 4096;
	private int[][] tabelaDeTransicao;
	private int estadoAtual ;
	private int[] bufferDeLeitura1;
	private int[] bufferDeLeitura2;
	private int bufferEmUso;
	private int posicaoDoBuffer;
	
	public Lexico() {
		tabelaDeTransicao = inicializaTabelaDeTransicao();
		estadoAtual = 0;
		bufferDeLeitura1 = new int[MAXBUFFER];
		bufferDeLeitura2 = new int[MAXBUFFER];
		posicaoDoBuffer = -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @param args
	 */
	public  int leProximoCaracter() {
		
		int char = leProximoCaracterDoBuffer();
		
		return 0;

	}

	private int leProximoCaracterDoBuffer() {
		if (bufferEmUso ==1){
			posicaoDoBuffer++;
			return bufferDeLeitura1[posicaoDoBuffer];
		}
		else if (bufferEmUso ==2){
			posicaoDoBuffer++;
			return bufferDeLeitura2[posicaoDoBuffer];
		}
	}

}
