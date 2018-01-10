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
public class ThenAcceptExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);

		System.out.println("[5] Example 5 - Using thenAccept and other ExecutorService");
		CompletableFuture.supplyAsync(() -> {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 1_000_000_000; i++) {
				if (i % 100_000_000 == 0) {
					System.out.print("5");
					builder.append((char)(new Random().nextInt(26) + 'A'));
				}
			}
			System.out.println("[5] DONE");
			return builder.toString();
		}, service).thenAccept(result -> System.out.println("[5] Future 5 is DONE. Result is: " + result));

		service.shutdown(); // no need to join() because this executor requires explicit shutdown
	}

}
