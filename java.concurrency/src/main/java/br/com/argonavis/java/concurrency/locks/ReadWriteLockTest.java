package br.com.argonavis.java.concurrency.locks;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		
		new Thread(() -> {
			sleep(3);
			if (lock.readLock().tryLock()) {
				try {
					System.out.println("read before");
					for (int i = 0; i < 30; i++) {
						sleep(25);
						System.out.println(
								SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
					}
					System.out.println("read after");
				} finally {
					System.out.println("unlocking read");
					lock.readLock().unlock();
				}
			}
		}).start();

		new Thread(() -> {
			try {
				lock.writeLock().lock();
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
				lock.writeLock().unlock();
			}

		}).start();

		new Thread(() -> {
			try {
				lock.writeLock().lock();
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
				lock.writeLock().unlock();
			}
		}).start();

	}

	public static void sleep(int maxTime) {
		try {
			Thread.sleep(new Random().nextInt(maxTime));
		} catch (InterruptedException e) {
		}
	}

}
