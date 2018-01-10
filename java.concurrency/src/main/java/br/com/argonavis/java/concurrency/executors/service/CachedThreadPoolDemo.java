package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
	public static void main(String[] args) {
		System.out.println("> 4 threads running in parallel");
		ExecutorService e = 
		     Executors.newCachedThreadPool();
		e.execute( new ConcurrentTask("Ch-One") );
		e.execute( new ConcurrentTask("Ch-Two") );
		e.execute( new ConcurrentTask("Ch-Three") );
		e.execute( new ConcurrentTask("Ch-Four") );
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> Executor will shutdown after tasks.");
	}
}
