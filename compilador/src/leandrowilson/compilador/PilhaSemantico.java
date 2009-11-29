package leandrowilson.compilador;

import java.util.Stack;

public class PilhaSemantico{
	Stack<ElementoSemantico> pilha = new Stack<ElementoSemantico>();
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	public void push(ElementoSemantico elSemantico) {
		pilha.push(elSemantico);
	}

	public ElementoSemantico pop() {
		return pilha.pop();
	}
	
	public ElementoSemantico peek(){
		return pilha.peek();
	}
}
