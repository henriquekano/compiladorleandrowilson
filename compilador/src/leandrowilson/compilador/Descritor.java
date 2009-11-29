package leandrowilson.compilador;

public class Descritor {
	
	private int end_true;
	private int end_false;
	
	TipoDescritor tipo;
	String label;
	Object[] valor;
	int posicaoInicial;
	int tamanho;
	private int[] indicesMaximos;
	private Descritor[] DescritorStrings;
	private int indicesMaximosTam;
	
	//VAL_INT E VAL_BOOL
	Descritor(Object valor, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		tamanho = 1;
		valor = new Object[tamanho];
		this.valor[0] = valor;
	}
	
	//VAL_STRING
	Descritor(Object[] valor, int tamanho, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.tamanho = tamanho;
		this.valor = valor;
	}
	
	//VAR_INT E VAR_BOOL
	Descritor(String label,  TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
	}
	
	//VAR_STRING
	Descritor(String label, int tamanho, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.tamanho = tamanho;
	}
	
	//VVAR_INT E VVAR_BOOL
	Descritor(String label, int[] indices, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indices;
	}
	
	//VVAR_STRING
	Descritor(String label, Descritor[] strings, int[] indicesMaximos, int indicesMaximosTam, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indicesMaximos;
		this.indicesMaximosTam = indicesMaximosTam; 
		this.DescritorStrings = strings;
	}
	
	void AtualizaPosicao(int posicao){
		this.posicaoInicial = posicao;
	}
	
	void AtualizaEndBool(int posicaoTrue, int posicaoFalse){
		end_false = posicaoFalse;
		end_true = posicaoTrue;
	}
	
	Object GetValor(){
		return valor;
	}
	
	int GetPosicao(int[] indices){
		int posicao = posicaoInicial + indicesMaximos[indicesMaximosTam-1];
		for(int i = indicesMaximosTam-2; i>=0; i--){
			posicao+=produtorio(i,indicesMaximosTam-1,indicesMaximos);
		}
		return posicao;
		
	}
	
	private int produtorio(int inicio, int fim, int[] vetor){
		int result = 1;
		for(int i = inicio; i<=fim; i++){
			result *=vetor[i];
		}
		return result;
		
	}
	
}
