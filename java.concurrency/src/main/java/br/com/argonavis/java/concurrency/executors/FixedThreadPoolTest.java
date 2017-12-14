package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
	public static void main(String[] args) {

		System.out.println("4 tentando rodar em pool de 2");
		ExecutorService e = 
		     Executors.newFixedThreadPool(2);
		e.execute( new ConcurrentTask("Fx-Um") );
		e.execute( new ConcurrentTask("Fx-Dois") );
		e.execute( new ConcurrentTask("Fx-Tres") );
		e.execute( new ConcurrentTask("Fx-Quatro") );
		e.shutdown(); // finaliza quando todos terminarem

	}
}
