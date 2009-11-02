package leandrowilson.compilador;

import java.io.File;
import java.util.Scanner;


public class Compilador {
	static Scanner scanner = new Scanner (System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lexico lex = new Lexico();
		String nomeDoArquivo;
		
		System.out.println("ESTE É O COMPILADOR DESENVOLVIDO POR LEANDRO CORDEIRO DAVID E WILSON FARIA\n\n");
		System.out.println("Digite o nome do arquivo que deseja compilar:");
		//nomeDoArquivo = scanner.next();
		nomeDoArquivo = "c:\\teste.txt";
		System.out.println("Lendo arquivo:"+nomeDoArquivo);
		nomeDoArquivo = nomeDoArquivo.replace("\\", "\\\\");
	    System.out.println("Lendo arquivo:"+nomeDoArquivo);
		List listaDeTokens = lex.obterListaDeTokens(nomeDoArquivo);
		imprimeListaDeTokens(listaDeTokens);
	}
	private static void imprimeListaDeTokens(List listaDeTokens) {
		Token token;
		for(int i =0;i<listaDeTokens.tamanho;i++){
			token = (Token)listaDeTokens.get(i);
			System.out.println(i+"-<"+token.tipo+","+String.valueOf(token.valor)+">");
		}
		
	}

}
