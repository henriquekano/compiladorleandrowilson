package leandrowilson.compilador;

public class ElementoSemantico {
	Escopo escopo;
	TipoMaquina tipoMaquina;
	Integer transicaoSemantica;
	PilhaSemantico pilhaSemnatico;
	
	public ElementoSemantico() {

	}

	public ElementoSemantico(Escopo escopo, TipoMaquina tipoMaquina,Integer transicaoSemantica, PilhaSemantico pilhaSemnatico) {
		this.escopo = escopo;
		this.tipoMaquina = tipoMaquina;
		this.transicaoSemantica = transicaoSemantica;
		this.pilhaSemnatico = pilhaSemnatico;
	}
	
}
