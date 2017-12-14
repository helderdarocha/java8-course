package br.com.argonavis.java.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolTest {
	public static void main(String[] args) throws Exception {
		System.out.println("Execução de 4 threads paralelamente em 2 processadores");
		ExecutorService e = 
		     Executors.newWorkStealingPool(2);
		e.execute( new ConcurrentTask("P-Um") );
		e.execute( new ConcurrentTask("P-Dois") );
		e.execute( new ConcurrentTask("P-Tres") );
		e.execute( new ConcurrentTask("P-Quatro") );
		
		e.awaitTermination(1, TimeUnit.SECONDS); // necessário fazer este thread (main) esperar
		e.shutdown(); // finaliza quando todos terminarem
	}
}
