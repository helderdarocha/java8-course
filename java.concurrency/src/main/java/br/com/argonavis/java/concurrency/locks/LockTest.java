package br.com.argonavis.java.concurrency.locks;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();

		new Thread(() -> {
			try {
				lock.lock();
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
			} finally {
				System.out.println("unlocking");
				lock.unlock();
			}

		}).start();

		new Thread(() -> {
			try {
				lock.lock();
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

			} finally {
				System.out.println("unlocking");
				lock.unlock();
			}
		}).start();
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}

}