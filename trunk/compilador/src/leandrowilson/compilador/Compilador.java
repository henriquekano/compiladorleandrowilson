package leandrowilson.compilador;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class Compilador {
	static Scanner scanner = new Scanner (System.in);
	public static Lexico lex = new Lexico();
	public static Sintatico sintatico;
	/**
	 * @param args
	 */
public static void main(String[] args) {
		

		String nomeDoArquivo =null;
		String arquivoSaida = null;
		System.out.println("ESTE É O COMPILADOR DESENVOLVIDO POR LEANDRO CORDEIRO DAVID E WILSON FARIA PARA A DISCIPLINA DE COMPILADORES DURANTE A HEELWEEK DE 2009\n\n");
		System.out.println("A linguagem de entrada é a C.rveja pois tudo começou com uma tentativa de copiar o C");
		System.out.println("Mas depois, em busca da felicidade a coisa se desvirtuou e a linguagem final tem muito pouco a ver com C");
		System.out.println("Entrada: Arquivo com código em linguagem C.rveja");
		System.out.println("Saída: Arquivo com o MVN gerado");
		System.out.println("Digite o caminho e nome do arquivo que deseja compilar(Ex.: C:\\Compiladores\\origem.txt):");
		nomeDoArquivo = scanner.next();
		System.out.println("Digite o caminho e nome do arquivo de saída(Ex.: C:\\Compiladores\\saida.mvn):");
		arquivoSaida = scanner.next();
//		nomeDoArquivo = "c:\\testeP2.txt";
		
		
//		nomeDoArquivo = "c:\\teste.txt";
//		nomeDoArquivo = "c:\\teste_reservadas.txt";
//		nomeDoArquivo = "c:\\testePrograma.txt";
//		nomeDoArquivo = "c:\\testeSemantico.txt";

		System.out.println("Lendo arquivo:"+nomeDoArquivo);
		nomeDoArquivo = nomeDoArquivo.replace("\\", "\\\\");
		arquivoSaida = arquivoSaida.replace("\\", "\\\\");
	    System.out.println("Lendo arquivo:"+nomeDoArquivo);
//	    testalexico(nomeDoArquivo);
	    List listaDeTokens =lex.obterListaDeTokens(nomeDoArquivo);
		sintatico = new Sintatico(listaDeTokens);
		if (sintatico.executa()){
			System.out.println("\nSintatico finalizado com sucesso!");
		}
		else{
			System.out.println("Sintatico finalizado com ERRO!");
			printErrorList(sintatico.getErros());
		}
		
		Gravar(sintatico.getCodigoFinal(),arquivoSaida);
		System.out.println("Arquivo Gerado !!!! \n\nFui Aprovado?(digite \"sim\")");
		String resposta = scanner.next();
		confirmaAprovação(resposta);
	}

		public static void Gravar(String texto,String arquivo){  
			System.out.println("\n\nGerando arquivo de saída:"+ arquivo);
		   String conteudo = texto;  
		   try{  
		      // o true significa q o arquivo será constante  
		      FileWriter x = new FileWriter(arquivo,true);   
		        
		     
		      conteudo += "\n\r"; // criando nova linha e recuo no arquivo           
		      x.write(conteudo); // armazena o texto no objeto x, que aponta para o arquivo           
		      x.close(); // cria o arquivo             
		   }  
		   // em caso de erro apreenta mensagem abaixo  
		   catch(Exception e){  
		       
		   }  
		} 
		private static void confirmaAprovação(String resposta) {
			while(!resposta.equals("sim") ){
				System.out.println("\n\nFala sério. Não seja cruel. Fui Aprovado?(basta responder \"sim\")");
				resposta = scanner.next();
			}
			System.out.println("UFA!!!!!   Valew \\o/ - Paz e Bem !!!");
		}

	private static void printErrorList(List erros) {
		Erro erro =null;
		for(int i =0;i<erros.tamanho;i++){
			erro =(Erro)erros.get(i);
			System.out.println(erro.tipo.mensagem+" Token:<"+erro.token.tipo.toString()+","+erro.token.valor+">"+" Linha:"+((erro.token.linha!=null)?erro.token.linha.toString():"") );
		}
	}
	private static void testalexico(String nomeDoArquivo) {
		List listaDeTokens = lex.obterListaDeTokens(nomeDoArquivo);
		imprimeListaDeTokens(listaDeTokens);
		
	}
	private static void imprimeListaDeTokens(List listaDeTokens) {
		Token token;
		for(int i =0;i<listaDeTokens.tamanho;i++){
			token = (Token)listaDeTokens.get(i);
			System.out.println(i+"-<"+token.tipo.toString()+","+String.valueOf(token.valor)+">");
		}
		
	}

}
