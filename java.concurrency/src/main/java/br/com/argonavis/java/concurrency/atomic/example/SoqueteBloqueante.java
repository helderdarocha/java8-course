package br.com.argonavis.java.concurrency.atomic.example;

public class SoqueteBloqueante {
	private Lampada lampada;

	public SoqueteBloqueante(Lampada lampada) {
		this.lampada = lampada;
	}

	public synchronized Lampada substituir(Lampada lampadaNova) {
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
