package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {
	public static void main(String[] args) {
		System.out.println("Execução de 4 threads em paralelo");
		ExecutorService e = 
		     Executors.newCachedThreadPool();
		e.execute( new ConcurrentTask("Ch-Um") );
		e.execute( new ConcurrentTask("Ch-Dois") );
		e.execute( new ConcurrentTask("Ch-Tres") );
		e.execute( new ConcurrentTask("Ch-Quatro") );
		e.shutdown(); // finaliza quando todos terminarem
	}
}
