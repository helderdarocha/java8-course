package br.com.argonavis.java.concurrency.collections.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SynchronousQueueExample {
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> q = new SynchronousQueue<>();
		ExecutorService e = Executors.newCachedThreadPool();
		e.execute(new Producer(q));
		Future<?> c1 = e.submit(new Consumer(q, "Consumer 1"));
		Future<?> c2 = e.submit(new Consumer(q, "Consumer 2"));
		
		try {
			c1.get(10, TimeUnit.SECONDS);
			c2.get(10, TimeUnit.SECONDS);
		} catch (ExecutionException | TimeoutException e1) {
			c1.cancel(true);
			c2.cancel(true);
		}
		e.shutdown();
	}

}