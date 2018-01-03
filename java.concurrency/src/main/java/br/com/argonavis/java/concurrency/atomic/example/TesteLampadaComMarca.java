package br.com.argonavis.java.concurrency.atomic.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Três threads tentam trocar uma lâmpada em 7 soquetes
 *
 */
public class TesteLampadaComMarca {
	
	static class TrocaDeLampada implements Runnable {
		
		private Lampada lampada;
		private SoqueteAtomicoMarcado[] soquetes;
		
		public TrocaDeLampada(SoqueteAtomicoMarcado[] soquetes, Lampada lampada) {
			this.soquetes = soquetes;
			this.lampada = lampada;
		}

		@Override public void run() {
			for(SoqueteAtomicoMarcado soquete : soquetes) {
				try {
					TimeUnit.MILLISECONDS.sleep(new Random().nextInt(200)); // to simulate delay
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			    soquete.substituir(lampada);
			}
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		final SoqueteAtomicoMarcado[] soquetes = new SoqueteAtomicoMarcado[7];
		for(int i = 0; i < soquetes.length; i++) {
			soquetes[i] = new SoqueteAtomicoMarcado(new Lampada(60, "Incandescente"), "Soquete " + (i+1));
			// marcar apenas soquetes pares para troca
			if(i % 2 == 0)
				soquetes[i].marcarLampadaParaTroca();
		}
		
		
		// Troca de lampadas
		Thread[] threads = new Thread[3];
		threads[0] = new Thread(new TrocaDeLampada(soquetes, new Lampada(5, "LED")));
		threads[0].setName("[Troca por LED]"); 
		threads[1] = new Thread(new TrocaDeLampada(soquetes, new Lampada(20, "Fluorescente")));
		threads[1].setName("[Troca por Fluorescente]");
		threads[2] = new Thread(new TrocaDeLampada(soquetes, new Lampada(40, "Halogena")));
		threads[2].setName("[Troca por Halogena]");
		
		for(Thread t : threads)
			t.start();
		
		for(Thread t : threads)
			t.join(1000);
			
		for(SoqueteAtomicoMarcado soquete : soquetes)
		    System.out.println("Lampada final "+soquete.getNome()+": " + soquete.get());

	}

}
