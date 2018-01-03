package br.com.argonavis.java.concurrency.atomic;

import java.util.Random;

public class AtomicTest {

	public static void main(String[] args) {
		System.out.println("Expected op1/op2 = 10/11 or 3/11");

		new Thread(() -> {
			System.out.println("operation1 before");
			long result = SharedResource.atomic.addAndGet(8);
			sleep(200);
			System.out.println("Result 1: " + result); 
			System.out.println("operation1 after");
		}).start();
		
		new Thread(() -> {
			System.out.println("operation2 before");
			long result = SharedResource.atomic.incrementAndGet();
			sleep(200);
			System.out.println("Result 2: " + result);
			System.out.println("operation2 after");
		}).start();
		
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}

}
