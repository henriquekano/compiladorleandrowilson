package leandrowilson.compilador;

public enum TipoMaquina {
	PROGRAMA(new TipoToken[] {TipoToken.PR_MAIN}),
	FUNCAO(new TipoToken[] {TipoToken.PR_MAIN}),
	PROCEDIMENTO(new TipoToken[] {TipoToken.PR_MAIN}),
	DECLARACAO(new TipoToken[] {TipoToken.NUMERO,TipoToken.ID,TipoToken.PR_STRING,TipoToken.PR_BOOLEAN,TipoToken.PR_STRUCT}),
	COMANDO(new TipoToken[] {TipoToken.PR_MAIN}),
	EXPRESSAO(new TipoToken[] {TipoToken.PR_MAIN}),
	EXPBOOLEANA(new TipoToken[] {TipoToken.PR_MAIN}),
	EXPRELACIONAL(new TipoToken[] {TipoToken.PR_MAIN}),
	EXPARITMETICA(new TipoToken[] {TipoToken.PR_MAIN}),
	EXPSTRING(new TipoToken[] {TipoToken.PR_MAIN});

	private final TipoToken[] first;
	public TipoToken[] first() {
		// TODO Auto-generated method stub
		return this.first;
	}
	TipoMaquina(TipoToken[] _first){
		this.first = _first;
		
	}
}
