package br.com.argonavis.java.classes;

public class Codigo {
	private long ativacao;
	private int parcial;
	private long completo;
	
	public Codigo(int parcial, long ativacao) {
		this.parcial = parcial;
		this.ativacao = ativacao;
		System.out.println("Ativação parcial");
	}
	
	public Codigo(long completo, long ativacao) {
		this.completo = completo;
		this.ativacao = ativacao;
		System.out.println("Ativação completa");
	}
	/*
	public Codigo(long completo, int parcial) {
		this.completo = completo;
		this.parcial  = parcial;;
		System.out.println("Completa e parcial");
	}
	*/
}