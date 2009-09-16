package leandrowilson.compilador.lexico;

public class TabelaDeTransicao {
	private int[][] tabelaDeTransicao;
	//Estado final de erro
	private final int ESTADO_ERRO =9999; 
	private final int ASCII_TAMANHO = 256;
    private final int ESTADOS_QUANT = 20;
    private int[] estadosFinais;
    
    
	public TabelaDeTransicao() {
		tabelaDeTransicao = new int[ESTADOS_QUANT][ASCII_TAMANHO];
		inicializaTabelaDeTransicaoVazia();
		inicializaEstadosFinais();
	}

	private void inicializaEstadosFinais() {
		// TODO Auto-generated method stub
		
	}

	private void inicializaTabelaDeTransicaoVazia() {
		for (int i = 0;i<ESTADOS_QUANT;i++){
			for (int j = 0;i<ASCII_TAMANHO;i++){
				tabelaDeTransicao[i][j]=ESTADO_ERRO;
			}

		}
	}
	
	public int proximoEstado(int estadoAtual,char caracterLido){
		return tabelaDeTransicao[estadoAtual][caracterLido];
	}
}
