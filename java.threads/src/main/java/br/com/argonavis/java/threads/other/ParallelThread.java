package br.com.argonavis.java.threads.other;

/**
 * This is an alternative way to create a thread (by extending Thread without creating a Runnable instance).
 *
 */
public class ParallelThread extends Thread {
    public volatile int contagem;
    
    private int espera;
    private String tipo;
    
    public ParallelThread(String tipo, int espera) {
    	this.tipo = tipo;
    	this.espera = espera;
    }
    public void run() {
        while (contagem < 60) {
            System.out.println(++contagem + " carneirinhos " + tipo + "...");
            try {
				Thread.sleep(espera);
			} catch (InterruptedException ignored) {} 
        }
        System.out.println("Não há mais carneirinhos " + tipo);
    }
}

