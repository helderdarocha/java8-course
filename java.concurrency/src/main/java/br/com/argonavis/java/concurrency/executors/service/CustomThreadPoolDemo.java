package br.com.argonavis.java.concurrency.executors.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.argonavis.java.concurrency.NamedPoolThreadFactory;

public class CustomThreadPoolDemo {
	public static ExecutorService newCustomThreadPool(int minThreads, int maxThreads, long timeout) {
		return new ThreadPoolExecutor(minThreads, 
				                      maxThreads, 
				                      timeout, TimeUnit.SECONDS, 
				                      new SynchronousQueue<Runnable>(),
				                      new NamedPoolThreadFactory("Demo-Thread"),
				                      new ThreadPoolExecutor.CallerRunsPolicy()); 
	}
	
	public static void main(String[] args) {

		System.out.println("4 threads on custom thread pool");
		ExecutorService e = newCustomThreadPool(1, 3, 10L);
		e.execute( new ConcurrentTask("Tp-One") );
		e.execute( new ConcurrentTask("Tp-Two") );
		e.execute( new ConcurrentTask("Tp-Three") );
		e.execute( new ConcurrentTask("Tp-Four") );
		e.shutdown(); // finaliza quando todos terminarem
		System.out.println("> Executor will shutdown after tasks."); 
	}
}
