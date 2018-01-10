package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		System.out.println("4 trying to run in pool of 3");
		ExecutorService e = 
		     Executors.newFixedThreadPool(3);
		e.execute( new ConcurrentTask("Fx-One") );
		e.execute( new ConcurrentTask("Fx-Two") );
		e.execute( new ConcurrentTask("Fx-Three") );
		e.execute( new ConcurrentTask("Fx-Four") );
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> Executor will shutdown after tasks.");
	}
}
