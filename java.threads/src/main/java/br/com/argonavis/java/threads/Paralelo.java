package br.com.argonavis.java.threads;

public class Paralelo implements Runnable {
	public volatile int contagem;

	private int espera;
	private String tipo;

	public Paralelo(String tipo, int espera) {
		this.tipo = tipo;
		this.espera = espera;
	}

	public void run() {
		while (contagem < 30) {
			System.out.println(++contagem + " carneirinhos " + tipo + "...");
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {
			} // espera 1 segundo
		}
		System.out.println("Não há mais carneirinhos " + tipo);
	}
}
