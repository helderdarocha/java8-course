package br.com.argonavis.java.concurrency.locks;

import java.util.Random;

public class SynchronizedTest {

	public static void main(String[] args) {

		synchronized(SynchronizedTest.class) {
			new Thread(() -> {
				synchronized(SynchronizedTest.class) {
					System.out.println("operation1 before");
					SharedResource.data[0] = 10;
					sleep(100);
					SharedResource.data[1] = 90;
					sleep(200);
					SharedResource.data[2] = SharedResource.data[0] + SharedResource.data[1];
					sleep(200);
					System.out.println(
							SharedResource.data[0] + " + " + SharedResource.data[1] + " = " + SharedResource.data[2]);
					System.out.println("operation1 after");
				}
			}).start();
			
			new Thread(() -> {
				synchronized(SynchronizedTest.class) {
					System.out.println("operation2 before");
					SharedResource.data[0] = 5;
					sleep(100);
					SharedResource.data[1] = 7;
					sleep(200);
					SharedResource.data[2] = SharedResource.data[0] * SharedResource.data[1];
					sleep(200);
					System.out.println(
							SharedResource.data[0] + " * " + SharedResource.data[1] + " = " + SharedResource.data[2]);
					System.out.println("operation2 after");
				}
			}).start();
		}
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}
}
