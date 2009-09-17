package leandrowilson.compilador.lexico;

public class TabelaDeTransicao {
	private int[][] tabelaDeTransicao;
	//Estado final de erro  - alterar posteriormente
	private final int ESTADO_ERRO =9999; 
	private final int ASCII_TAMANHO = 256;
    private final int ESTADOS_QUANT = 12;
    private int[] estadosFinais;
    
    
	public TabelaDeTransicao() {
		tabelaDeTransicao = new int[ESTADOS_QUANT][ASCII_TAMANHO];
		inicializaTabelaDeTransicaoVazia();
		inicializaEstadosFinais();
		inicializaTabelaDeTransicao();
	}

	private void inicializaEstadosFinais() {
		estadosFinais = new int[ESTADOS_QUANT];
		estadosFinais[0] = 0;
		estadosFinais[1] = 8;
		estadosFinais[2] = 10;
		estadosFinais[3] = 11;
		
	}

	private void inicializaTabelaDeTransicaoVazia() {
		//preenche toda a Tabela de Tranição com Estado de Erro
		for (int i = 0;i<ESTADOS_QUANT;i++){
			for (int j = 0;i<ASCII_TAMANHO;i++){
				tabelaDeTransicao[i][j]=ESTADO_ERRO;
			}

		}
	}
	
	public int proximoEstado(int estadoAtual,int caracterLido){
		//Retorna o devido elemento da tabela de transição
		return tabelaDeTransicao[estadoAtual][caracterLido];
	}
	
	private void inicializaTabelaDeTransicao() {
		//Preenche a Tabela de Transição
		//tabelaDeTransicao[0][0] = 0;
	}
}
