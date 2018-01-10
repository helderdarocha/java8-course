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
public class ThenApplyExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);

		System.out.println("[6] Example 6 - Cascading thenApply + thenAccept");
		TagOperations to = new TagOperations();
		
		CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "text", service)
			.thenApply(s -> to.tag("p", s))
			.thenApply(s -> s.concat("\n"))
			.thenApply(s -> "\n".concat(s))
			.thenApply(s -> to.tag("div", s));
		
		future6.thenAccept(s -> System.out.println("[6] Future 6 result:\n" + s));
		
		System.out.println("\n[7] Example 7 - Asynchronous tasks with thenApplyAsync");

		CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "async-text", service)
			.thenApplyAsync(s -> to.tag("inner", s))
			.thenApplyAsync(s -> s.concat("\n"))
			.thenApplyAsync(s -> "\n".concat(s))
			.thenApplyAsync(s -> to.tag("outer", s));
		
		future7.thenAcceptAsync(s -> System.out.println("[7] Future 7 result:\n" + s));
		
		System.out.println("\n[8] Example 8 - Changing types - String to integer");
		System.out.println();
		CompletableFuture<Integer> future8 = future7.thenApply(s -> s.length());
		System.out.println("[8] Result: " + future8.get());
	
		service.shutdown();
	}

}
