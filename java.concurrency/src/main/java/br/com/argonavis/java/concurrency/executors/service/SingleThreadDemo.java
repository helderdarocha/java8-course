package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadDemo {
	public static void main(String[] args) {
		System.out.println("Execution a sequence of 4 tasks in one thread.");
		ExecutorService e = 
		     Executors.newSingleThreadExecutor();
		e.execute( new ConcurrentTask("Sg-One") );
		e.execute( new ConcurrentTask("Sg-Two") );
		e.execute( new ConcurrentTask("Sg-Three") );
		e.execute( new ConcurrentTask("Sg-Four") );
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> Executor will shutdown after tasks.");
	}
}
