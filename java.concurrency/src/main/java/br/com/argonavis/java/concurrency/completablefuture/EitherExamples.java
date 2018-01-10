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
public class EitherExamples {

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
		
		// applyToEither
		System.out.println("[9] Example 9 - applyToEither");
		CompletableFuture<Integer> future9 = future6.applyToEither(future7, (n)-> n.length() * 100);
		System.out.println("[9] Result (one of the numbers): " + future9.get());
		
		// acceptEither
		System.out.println("[10] Example 10 - acceptEither");
		future8.acceptEither(future9, (n)-> System.out.println("Result: " + n));
		
		// runAfterEither
		System.out.println("[10] Example 11 - runAfterEither");
		future8.runAfterEither(future9, ()-> System.out.println("After f8 or f9"));

	}

}
