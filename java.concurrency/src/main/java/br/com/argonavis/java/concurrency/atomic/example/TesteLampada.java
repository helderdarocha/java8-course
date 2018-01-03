package br.com.argonavis.java.concurrency.atomic.example;

public class TesteLampada {

	public static void main(String[] args) throws InterruptedException {
		Lampada inicial = new Lampada(60, "Incandescente");
		//Soquete soquete = new Soquete(inicial);
		//SoqueteBloqueante soquete = new SoqueteBloqueante(inicial);
		SoqueteAtomico soquete = new SoqueteAtomico(inicial);
		
		// Troca de lampadas
		Thread[] threads = new Thread[3];
		threads[0] = new Thread(() -> System.out.println(Thread.currentThread().getName() 
									+ " substituindo " 
									+ soquete.substituir(new Lampada(5,  "LED")) 
									+ " por LED 5W."));
		threads[1] = new Thread(() -> System.out.println(Thread.currentThread().getName() 
									+ " substituindo " 
									+ soquete.substituir(new Lampada(20, "Fluorescente")) 
									+ " por Fluorescente 20W."));
		threads[2] = new Thread(() -> System.out.println(Thread.currentThread().getName()  
									+ " substituindo " 
									+ soquete.substituir(new Lampada(40, "Halogena")) 
									+ " por Halogena 40W."));
		
		for(Thread t : threads)
			t.start();
		
		for(Thread t : threads)
			t.join();
			
		System.out.println("Lampada final: " + soquete.get());

	}

}
