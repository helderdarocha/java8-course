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
public class ExeptionallyHandleExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);

		System.out.println("[11] Example 11 - exceptionally");

		CompletableFuture.supplyAsync(() -> "text", service)
			.thenApply(s -> {
				if(s.equals("text")) 
					throw new RuntimeException("Write something creative!");
				return s;
			})
			.exceptionally(e -> {
				System.out.println("[11] Exception occurred. Replacing text.");
				return "Creative Text";
			})
			.thenAcceptAsync(s -> System.out.println("[11] Future 11 result:\n" + s));
		
		System.out.println("\n[11] Example 12 - handle");
		
		CompletableFuture.supplyAsync(() -> new Random().nextInt(2)==0 ? "text" : "normal text", service)
		.thenApply(s -> {
			if(s.equals("text")) 
				throw new RuntimeException("Write something creative!");
			return s;
		})
		.handle((data, error) -> {
			if(error != null) {
			    System.out.println("[12] Error occurred. Replacing text.");
			    return "Creative Text";
			} else {
				return data;
			}
		})
		.thenAcceptAsync(s -> System.out.println("[12] Future 12 result:\n" + s));
		service.shutdown();
	}

}
