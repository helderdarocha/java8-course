package br.com.argonavis.java.concurrency.atomic.example;

public class Soquete {
	private Lampada lampada;

	public Soquete(Lampada lampada) {
		this.lampada = lampada;
	}

	public Lampada substituir(Lampada lampadaNova) {
		Lampada lampadaVelha = this.lampada;
		//try { Thread.sleep(500); } catch (InterruptedException e) {} 
		if (lampadaVelha == this.lampada) {
			this.lampada = lampadaNova;
		}
		return lampadaVelha;
	}

	public Lampada get() {
		return lampada;
	}
}
