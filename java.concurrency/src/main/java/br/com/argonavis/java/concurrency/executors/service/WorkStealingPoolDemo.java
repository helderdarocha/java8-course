package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPoolDemo {
	public static void main(String[] args) throws Exception {
		System.out.println("Execution of 4 threads using 2 processors");
		ExecutorService e = 
		     Executors.newWorkStealingPool(2);
		e.execute( new ConcurrentTask("P-One") );
		e.execute( new ConcurrentTask("P-Two") );
		e.execute( new ConcurrentTask("P-Three") );
		e.execute( new ConcurrentTask("P-Four") );
		
		e.awaitTermination(1, TimeUnit.SECONDS); // necessário fazer este thread (main) esperar
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> All tasks done.");
	}
}
