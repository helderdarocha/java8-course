package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
	public static void main(String[] args) {
		System.out.println("4 scheduled threads trying to run in pool of 2");
		ScheduledExecutorService e = 
		     Executors.newScheduledThreadPool(2);
		e.schedule( new ConcurrentTask("Sc-One"), 3, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Two"), 3, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Three"), 2, TimeUnit.SECONDS);
		e.schedule( new ConcurrentTask("Sc-Four"), 1, TimeUnit.SECONDS);
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> Executor will shutdown after tasks.");
	}
}
