package leandrowilson.compilador;

import java.util.Stack;

public class PilhaSemantico{
	Stack<Token> pilha = new Stack<Token>();
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	public void push(Token token) {
		pilha.push(token);
	}

	public Token pop() {
		return pilha.pop();
	}
	
	public Token peek(){
		return pilha.peek();
	}
}
