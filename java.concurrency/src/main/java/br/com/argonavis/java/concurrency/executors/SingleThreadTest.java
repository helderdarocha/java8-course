package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadTest {
	public static void main(String[] args) {
		System.out.println("Execução de 8 threads sequencialmente");
		ExecutorService e = 
		     Executors.newSingleThreadExecutor();
		e.execute( new ConcurrentTask("Sg-Um") );
		e.execute( new ConcurrentTask("Sg-Dois") );
		e.execute( new ConcurrentTask("Sg-Tres") );
		e.execute( new ConcurrentTask("Sg-Quatro") );
		e.shutdown(); // finaliza quando todos terminarem
	}
}
