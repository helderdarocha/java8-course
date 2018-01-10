package br.com.argonavis.java.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenComposeExample {

	public static void main(String[] args) {
		CompletableFuture<String> stage1 = new CompletableFuture<>();
		CompletableFuture<String> stage2 = CompletableFuture.supplyAsync(() -> "World");
		
		CompletableFuture<CompletableFuture<String>> stage3 = 
		    stage1.thenApply(s1 -> 
		        stage2.thenApply(s2 -> s1 + s2.toUpperCase()));
		stage3.thenAccept(cf -> System.out.println("S3: " + cf.join()));
		
		CompletableFuture<String> stage4 = 
        		stage1.thenCompose(s1 -> 
        		     stage2.thenApply(s2 -> s1 + s2.toUpperCase()));
		stage4.thenAccept(s -> System.out.println("S4: " + s));

		stage1.complete("Hello, ");
		stage1.join();
	}
}
