package leandrowilson.compilador;

import java.util.Stack;

public class PilhaSemantico{
	Stack<Object> pilha = new Stack<Object>();
	
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	public void push(Token token) {
		pilha.push(token);
	}
	
	public void push(Descritor descritor) {
		pilha.push(descritor);
	}
	
	public void push(List lista) {
		pilha.push(lista);
	}
	
	public List pop_List() {
		return (List)pilha.pop();
	}

	public Token pop_Token() {
		return (Token)pilha.pop();
	}
	
	public Descritor pop_Descritor() {
		return (Descritor)pilha.pop();
	}
	
	public Token peek_Token(){
		return (Token)pilha.peek();
	}
	
	public Descritor peek_Descritor(){
		return (Descritor)pilha.peek();
	}
	
	public List peek_Lista(){
		return (List)pilha.peek();
	}
}
