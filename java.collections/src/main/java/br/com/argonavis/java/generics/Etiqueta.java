package br.com.argonavis.java.generics;

public class Etiqueta<T1, T2> {
	private T1 codigo;
	private T2 item;

	public void setCodigo(T1 codigo) {
		this.codigo = codigo;
	}

	public void setItem(T2 item) {
		this.item = item;
	}
	
	public T1 getCodigo() {
		return codigo;
	}
	
	public T2 getItem() {
		return item;
	}
	
	public String toString() {
		return "codigo=" + codigo + ", item=" + item;
	}
	
}
