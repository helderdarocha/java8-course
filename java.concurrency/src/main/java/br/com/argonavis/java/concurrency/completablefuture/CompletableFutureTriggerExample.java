package br.com.argonavis.java.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import br.com.argonavis.java.concurrency.Utils;

/**
 * See https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
public class CompletableFutureTriggerExample {

	static class DoubleSupplier implements Supplier<Double> {
		CompletableFuture<Double> trigger;
		DoubleSupplier(CompletableFuture<Double> trigger) {
			this.trigger = trigger;
		}
		@Override
		public Double get() {
			try {
				return trigger.get(); // will block waiting for trigger
			} catch (ExecutionException | InterruptedException e) {
				return 0.0;
			} //
		}
	};

	public static void main(String[] args)  {

		// New
		CompletableFuture<Double> trigger = new CompletableFuture<>();

		// Chain
		CompletableFuture<Void> chain = CompletableFuture.supplyAsync(new DoubleSupplier(trigger))
		.thenApply(x -> x * x)
		.thenAccept(x -> System.out.print(x + " "))
		.thenRun(() -> System.out.println("DONE."));
		
		// Trigger in new thread
		trigger.completeAsync(() -> 9.0);
		
		
		    try {
				chain.get(1, TimeUnit.MILLISECONDS);
				System.out.println("A tarefa terminou.");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				System.out.println("Timeout");
			}
		    
		

		
		chain.join(); // join this thread (or get())

	}

}
