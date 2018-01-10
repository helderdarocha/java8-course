package br.com.argonavis.java.concurrency.completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * See https://www.callicoder.com/java-8-completablefuture-tutorial/
 */
public class ThenRunExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("[4] Example 4 - Using thenRun");
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0)
					System.out.print("2");
			}
			System.out.println("[2] DONE");
		}).thenRun(() -> System.out.println("[4] Future 4 is DONE"));
		
		future2.join(); // necessary because default pool is ForkJoinPool which uses daemon threads 
	}
}
