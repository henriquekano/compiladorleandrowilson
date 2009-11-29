package leandrowilson.compilador;

public class Descritor {
	
	private int end_true = 111; //MUDAR O ENDEREÇO PRO ENDEREÇO CERTO!!!
	private int end_false = 222; //MUDAR O ENDEREÇO PRO ENDEREÇO CERTO!!!
	
	TipoDescritor tipo;
	String label;
	Object[] valor;
	int posicaoInicial;
	int tamanho;
	
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
	}
	
	//VAR_STRING
	Descritor(String label, Descritor[] strings, int[] indices, TipoDescritor tipoDescritor){
		this.tipo = tipoDescritor;
		this.label = label;
		this.tamanho = tamanho;
	}
	
	void AtualizaPosicao(int posicao){
		this.posicaoInicial = posicao;
	}
	
	Object GetValor(){
		return valor;
	}
	
	/*Object GetValor(int posicao){
		return valor[posicao];
	}*/
	
}
