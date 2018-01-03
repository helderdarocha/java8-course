package br.com.argonavis.java.concurrency.atomic;

import java.util.Random;

public class SimpleTest {

	public static void main(String[] args) {
		System.out.println("Expected op1/op2 = 10/11 or 3/11");
		
		new Thread(() -> { //if first, result = 10, else 11
			System.out.println("operation1 before");
			sleep(200);
			long result = SharedResource.simple += 8; // this operation *may* fail
			System.out.println("Result 1: " + result); // if fails result will be 2 or 3
			System.out.println("operation1 after");
		}).start();
		
		new Thread(() -> { // if first, result = 3, else 11
			System.out.println("operation2 before");
			sleep(200);
			long result = SharedResource.simple = SharedResource.simple + 1;  // this operation *may* fail
			System.out.println("Result 2: " + result); // if fails, result will be 2 or 10
			System.out.println("operation2 after");
		}).start();
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}

}
