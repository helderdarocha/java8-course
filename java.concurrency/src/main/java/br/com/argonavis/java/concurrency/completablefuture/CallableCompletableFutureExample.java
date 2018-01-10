package br.com.argonavis.java.concurrency.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableCompletableFutureExample {
	
	/**
	 * https://stackoverflow.com/questions/30559707/completablefuture-from-callable
	 */
	public static <T> CompletableFuture<T> asFuture(Callable<? extends T> callable, Executor executor) {
	    CompletableFuture<T> future = new CompletableFuture<>();
	    executor.execute(() -> {
	        try {
	            future.complete(callable.call());
	        } catch (Throwable t) {
	            future.completeExceptionally(t);
	        }
	    });
	    return future;
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Callable<Integer> callable = new Callable<Integer>() {
			@Override public Integer call() throws Exception {
				return 123;
			}
		};
		
		CompletableFuture<Integer> cf = asFuture(callable, executor);
		cf.thenAccept(n -> System.out.println(n));
		executor.shutdown();
	}


}
