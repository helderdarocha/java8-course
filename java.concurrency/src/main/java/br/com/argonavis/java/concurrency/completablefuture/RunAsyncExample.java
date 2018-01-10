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
public class RunAsyncExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("[2] Example 2 - Asynchronous computation with Runnable");
		System.out.println("[2] Create future 2.");
		CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0)
					System.out.print("2");
			}
			System.out.println("[2] DONE");
		});
		
		System.out.println("[2] Will block future 2 until done.");
		try {
			future2.get();
			System.out.println("[2] Future 2 is DONE");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
