package leandrowilson.compilador.lexico;

public class TabelaDeTransicao {
	private int[][] tabelaDeTransicao;
	//Estado final de erro  - alterar posteriormente
	public final int ESTADO_INICIAL = 0;
	public final int ESTADO_ERRO =7; 
	private final int ASCII_TAMANHO = 256;
    private final int ESTADOS_QUANT = 8;
    private int[] estadosFinais;
    
    
	public TabelaDeTransicao() {
		System.out.println("Construtor da tabela de transiçao");
		tabelaDeTransicao = new int[ESTADOS_QUANT][ASCII_TAMANHO];	
		iniciaTabelaDeTransicao();
		iniciaEstadosFinais();
	}

	private void iniciaEstadosFinais() {
		System.out.println("iniciaEstadosFinais");
		estadosFinais = new int[ESTADOS_QUANT];
		for (int i=0;i<ESTADOS_QUANT;i++){
			estadosFinais[i] = ESTADO_ERRO;
		}
		//estadosFinais[0] = 0;
		//estadosFinais[1] = 8;
		//estadosFinais[2] = 10;
		//estadosFinais[3] = 11;
		estadosFinais[0] = 3;
		estadosFinais[1] = 6;
		
	}

	
	public int proximoEstado(int estadoAtual,int caracterLido){
		//Retorna o devido elemento da tabela de transição
		System.out.println("Proximo estado:estadoAtual:"+String.valueOf(estadoAtual)+" caracterLido:"+String.valueOf(caracterLido));
		return tabelaDeTransicao[estadoAtual][caracterLido];
	}
	
	private void iniciaTabelaDeTransicao() {
		System.out.println("iniciaTabelaDeTransicao");
		//preenche toda a Tabela de Tranição com Estado de Erro
		for (int i = 0;i<ESTADOS_QUANT;i++){
			for (int j = 0;j<ASCII_TAMANHO;j++){
				tabelaDeTransicao[i][j]=ESTADO_ERRO;
			}
		}		
		//Preenche a Tabela de Transição
		//tabelaDeTransicao[ESTADO][CARACTER] = 0;
		//tabelaDeTransicao[0][0] = 0;
		tabelaDeTransicao[0]['a'] = 1;
		tabelaDeTransicao[1]['b'] = 2;
		tabelaDeTransicao[2]['c'] = 3;
		tabelaDeTransicao[0]['1'] = 4;
		tabelaDeTransicao[4]['2'] = 5;
		tabelaDeTransicao[5]['3'] = 6;
		
		
	}

	public boolean estadoFinal(int estado) {

		boolean achouEstadoFinal = false;
		for (int i = 0;i<ESTADOS_QUANT;i++)
		{
			achouEstadoFinal=achouEstadoFinal || (estadosFinais[i]==estado);
		}
		if (achouEstadoFinal) {System.out.println("Estado "+estado+" é Estado final ");}
		else {System.out.println("Estado "+estado+" NAO é Estado final ");}
		
		return achouEstadoFinal;
	}

	public boolean estadoInicial(int estadoAtual) {
		return estadoAtual==ESTADO_INICIAL;
	}
}
