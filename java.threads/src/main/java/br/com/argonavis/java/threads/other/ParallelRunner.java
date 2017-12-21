package br.com.argonavis.java.threads.other;

public class ParallelRunner implements Runnable {
	public volatile int contagem;

	private int espera;
	private String tipo;

	public ParallelRunner(String tipo, int espera) {
		this.tipo = tipo;
		this.espera = espera;
	}

	public void run() {
		while (contagem < 30) {
			System.out.println(++contagem + " carneirinhos " + tipo + "...");
			try {
				Thread.sleep(espera);
			} catch (InterruptedException ignored) {} 
		}
		System.out.println("Não há mais carneirinhos " + tipo);
	}
}
