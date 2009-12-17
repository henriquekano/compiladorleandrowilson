package leandrowilson.compilador;

public class Descritor {
	
	private int end_true;
	private int end_false;
	
	TipoDescritor tipo;
	String label;
	Object[] valor;
	Integer posicaoInicial;
	Integer tamanho;
	public Integer[] indicesMaximos;
	public Descritor[] DescritorStrings;
	public Integer indicesMaximosTam;
	
	//VAL_INT E VAL_BOOL
	Descritor(Object val, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		tamanho = 1;
		valor = new Object[tamanho];
		for(int i = 0; i<tamanho; i++){
			valor[i] = new Object();
		}
		valor[0] = val;
	}
	
	//VAL_STRING
	Descritor(Object[] valor, Integer tamanho, TipoDescritor tipoDescritor){
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
	Descritor(String label, Integer tamanho, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.tamanho = tamanho;
	}
	
	//VVAR_INT E VVAR_BOOL
	Descritor(String label, Integer[] indices, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indices;
		this.indicesMaximosTam = indices.length;
	}
	
	//VVAR_STRING
	Descritor(String label, Descritor[] strings, Integer[] indicesMaximos, Integer indicesMaximosTam, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.indicesMaximos = indicesMaximos;
		this.indicesMaximosTam = indicesMaximosTam; 
		this.DescritorStrings = strings;
	}
	
	void AtualizaEndBool(Integer posicaoTrue, Integer posicaoFalse){
		end_false = posicaoFalse;
		end_true = posicaoTrue;
	}
	void AtualizaLabel(String lbl){
		this.label = label;
	}
	
	Object GetValor(){
		return valor;
	}
	String GetLabel(){
		return label;
	}
	
	int GetPosicao(Integer[] indices){
		Integer posicao = posicaoInicial + indicesMaximos[indicesMaximosTam-1];
		for(Integer i = indicesMaximosTam-2; i>=0; i--){
			posicao+=produtorio(i,indicesMaximosTam-1,indicesMaximos);
		}
		return posicao;
	}
	
	private int produtorio(Integer inicio, Integer fim, Integer[] vetor){
		int result = 1;
		for(int i = inicio; i<=fim; i++){
			result *=vetor[i];
		}
		return result;
		
	}

	public Integer GetIndice(Integer[] indices) {
		Integer posicao = posicaoInicial + indicesMaximos[indicesMaximosTam-1];

		
		Integer fator =1;
		posicao=0;
		Integer index;
		for (int i=0;i<indices.length;i++){
			index = indices[indices.length -1-i];
			posicao += fator * index;
			fator = indicesMaximos[indices.length -1-i];
		}
		return posicao;
	}
	

	
}
