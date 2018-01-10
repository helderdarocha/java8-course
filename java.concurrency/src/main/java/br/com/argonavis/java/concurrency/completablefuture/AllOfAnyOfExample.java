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
public class AllOfAnyOfExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TagOperations to = new TagOperations();
		
		CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> "text")
			.thenApply(s -> to.tag("p", s))
			.thenApply(s -> s.concat("\n"))
			.thenApply(s -> "\n".concat(s))
			.thenApply(s -> to.tag("div", s));

		CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> "async-text")
			.thenApplyAsync(s -> to.tag("inner", s))
			.thenApplyAsync(s -> s.concat("\n"))
			.thenApplyAsync(s -> "\n".concat(s))
			.thenApplyAsync(s -> to.tag("outer", s));
		
		CompletableFuture<Integer> future8 = future7.thenApply(s -> s.length());
		CompletableFuture<Integer> future9 = future6.thenCombine(future8, (f6,f8)-> f6.length() + f8);

		
		
		System.out.println("\n[10] Example 10 - allOf");
		List<CompletableFuture<?>> futures = List.of(future7, future9, future6, future8);
		CompletableFuture<?>[] array = futures.toArray(new CompletableFuture[futures.size()]);
		
		// AllOf
		CompletableFuture.allOf(array)
			.thenApply(v -> futures.stream().map(f -> f.join()).collect(Collectors.toList()))
			.thenAccept(s -> System.out.println("[10] Future 10 result: " + s + ""));
		
		// AnyOf
		System.out.println("\n[11] Example 11 - anyOf");
		CompletableFuture.anyOf(array)
		    .thenAccept(s -> System.out.println("[11] Future 11 result: " + s + ""));

	}

}
