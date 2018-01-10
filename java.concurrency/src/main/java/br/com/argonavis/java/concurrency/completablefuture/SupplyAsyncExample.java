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
public class SupplyAsyncExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("[3] Example 3 - Asynchronous computation with supplyAsync & get");
		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0) {
					System.out.print("3");
					builder.append((char)(new Random().nextInt(26) + 'A'));
				}
			}
			System.out.println("[3] DONE");
			return builder.toString();
		});

		System.out.println("[3] Will block future 3 until done.");
		try {
			String result = future3.get();
			System.out.println("[3] Future 3 result is: " + result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
