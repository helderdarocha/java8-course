package br.com.argonavis.java.concurrency.synchronizers;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class LatchDemo2 {

	static AtomicInteger count = new AtomicInteger(0);

	static class NumberGenerator implements Runnable {
		CountDownLatch latch;

		NumberGenerator(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				long num = new Random().nextInt(100);
				for (int i = 0; i < num ; i++) {
					latch.countDown();
				}
				System.out.println("Gen: " + num + ", Latch: " + latch.getCount());
				latch.await();
				System.out.println(num + " is free!");
			} catch (InterruptedException ignored) {}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(200);

		new Thread(new NumberGenerator(latch)).start();
		new Thread(new NumberGenerator(latch)).start();
		new Thread(new NumberGenerator(latch)).start();
		new Thread(new NumberGenerator(latch)).start();
		new Thread(new NumberGenerator(latch)).start();
		
		latch.await();
		System.out.println("Latch was opened!");

	}

}
