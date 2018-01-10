package br.com.argonavis.java.concurrency.executors.futures;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExecutorServiceDemo {
	
	static class Calculator {
		public static double computation(double result, int counter) {
			try {Thread.sleep(10);} catch (InterruptedException e) {}
			if(counter > 100) {
				System.out.println('x');
				return result;
			} else {
				if(counter % 10 == 0) {
					System.out.print('.');
				}
				return computation(Math.sqrt(result*(1+new Random().nextInt(counter))), ++counter);
			}
		}
	}
	
	static class Task implements Callable<Double>{
		@Override
		public Double call() {
			return Calculator.computation(1.0, 1);
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = 
				Executors.newFixedThreadPool(6);
				//Executors.newWorkStealingPool(4); // CompletableFuture tasks fail (MacOS) with this pool
				//Executors.newCachedThreadPool();
				//Executors.newSingleThreadExecutor();
		
		System.out.println("Starting sync tasks.");
		long beginSync = System.currentTimeMillis();
		Future<Double> result1 = executor.submit(new Task());
		Future<Double> result2 = executor.submit(new Task());
		Future<Double> result3 = executor.submit(new Task());

		System.out.println("Sync 1: " + result1.get());
		System.out.println("Task 1 done after " + (System.currentTimeMillis() - beginSync) + " ms");
		System.out.println("Sync 2: " + result2.get());
		System.out.println("Task 2 done after " + (System.currentTimeMillis() - beginSync) + " ms");
		System.out.println("Sync 3: " + result3.get());
		System.out.println("Task 3 done after " + (System.currentTimeMillis() - beginSync) + " ms");
		
		System.out.println("Starting async tasks.");
		long beginAsync = System.currentTimeMillis();
		CompletableFuture.supplyAsync(()->Calculator.computation(1.0, 1), executor)  
						 .thenAccept(n->System.out.println("Async 1: " + n + " (done after " + (System.currentTimeMillis() - beginAsync) + " ms)"));
		CompletableFuture.supplyAsync(()->Calculator.computation(1.0, 1), executor)  
		   				 .thenAccept(n->System.out.println("Async 2: " + n + " (done after " + (System.currentTimeMillis() - beginAsync) + " ms)"));
		CompletableFuture.supplyAsync(()->Calculator.computation(1.0, 1), executor)  
			 			 .thenAccept(n->System.out.println("Async 3: " + n + " (done after " + (System.currentTimeMillis() - beginAsync) + " ms)"));
		
		executor.shutdown();
	}

}
