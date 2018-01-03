package br.com.argonavis.java.concurrency.atomic.example;

public class Lampada {
	private final int watts;
	private final String tipo;

	Lampada(int watts, String tipo) {
		this.watts = watts;
		this.tipo = tipo;
	}

	public int getWatts() {
		return this.watts;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	@Override public String toString() {
		return tipo + " " + watts + "W";
	}
}
