package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleExecutorsDemo {

	public static void main(String[] args) {
		System.out.println("3 sequential tasks in parallel with one long task.");
		ExecutorService e1 = Executors.newSingleThreadExecutor();
		ExecutorService e2 = Executors.newSingleThreadExecutor();
		e1.execute( new ExclusiveTask() );
		e2.execute( new ConcurrentTask("Bg-One"));
		e2.execute( new ConcurrentTask("Bg-Two"));
		e2.execute( new ConcurrentTask("Bg-Three"));
		e1.shutdown();
		System.out.println("> Single task executor will shutdown after task.");
		e2.shutdown();
		System.out.println("> Sequential tasks executor will shutdown after tasks.");
	}

}
