package br.com.argonavis.java.generics;

public class Pacote<E> {
	
	private E conteudo;
	
	public Pacote(E conteudo) {
		this.conteudo = conteudo;
	}
	
	public E getConteudo() {
		return conteudo;
	}

	public void setConteudo(E conteudo) {
		this.conteudo = conteudo;
	}
	
	public String toString() {
		return conteudo.toString();
	}
}
