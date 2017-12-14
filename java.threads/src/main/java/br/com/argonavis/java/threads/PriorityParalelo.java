package br.com.argonavis.java.threads;

public class PriorityParalelo implements Runnable {
	public volatile int contagem;

	private String tipo;

	public PriorityParalelo(String tipo) {
		this.tipo = tipo;
	}

	public void run() {
		while (contagem < 10) {
			System.out.println(++contagem + " carneirinhos " + tipo + "...");
			//Thread.yield(); // allows for same priority threads to run (just as example - don't use this method - not portable)
		}
		System.out.println("Não há mais carneirinhos " + tipo);
	}
}
