package br.com.argonavis.java.concurrency.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletedFutureExample {

	public static void main(String[] args) {

		CompletableFuture<String> future = CompletableFuture.completedFuture("Hello!");
		System.out.println("It's done: " + future.isDone()); 
		String result = future.join(); 
		System.out.println(result);
	}

}
