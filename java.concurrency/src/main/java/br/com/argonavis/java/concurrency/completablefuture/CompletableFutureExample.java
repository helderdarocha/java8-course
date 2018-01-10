package br.com.argonavis.java.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Supplier;

import br.com.argonavis.java.concurrency.Utils;

/**
 * See https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
public class CompletableFutureExample {

	static Runnable runnableTask = new Runnable() {
		@Override
		public void run() {
			Utils.log("Runnable running.");
		}
	};

	static class DoubleSupplier implements Supplier<Double> {
		CompletableFuture<Double> trigger;
		DoubleSupplier(CompletableFuture<Double> trigger) {
			this.trigger = trigger;
		}
		@Override
		public Double get() {
			try {
				return trigger.get();
			} catch (ExecutionException | InterruptedException e) {
				return 0.0;
			}
		}
	};

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		// RunAsync and SupplyAsync
		System.out.println("[1] Example 1 - CompletableFuture from a task");
		CompletableFuture<Void> futureRunnable = CompletableFuture.runAsync(runnableTask);
		CompletableFuture<Integer> futureSupplier = CompletableFuture.supplyAsync(() -> 123);
		
		System.out.println("[1] Supplier: " + futureSupplier.get());
		futureRunnable.thenRun(() -> Utils.log("Runnable finished."));

		System.out.println("\n[2] Example 2 - new CompletableFuture and Callback");
		System.out.println("[2] Create future 1.");
		CompletableFuture<String> future1 = new CompletableFuture<String>();

		System.out.println("[2] Setup callback for future 1.");
		new Thread(() -> {
			try {
				System.out.println("[2] Result of future 1: " + future1.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}).start();

		System.out.println("[2] Send completion notification for future 1.");
		future1.complete("[2] It's DONE.");

	}

}
