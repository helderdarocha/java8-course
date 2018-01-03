package br.com.argonavis.java.concurrency.locks;

import static br.com.argonavis.java.concurrency.Utils.simulatedPause;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {

	public static void main(String[] args) {
		StampedLock lock = new StampedLock();

		new Thread(() -> {
			long stamp = -1;
			try {
				stamp = lock.writeLock();
				System.out.println("operation1 before");
				SharedResource.data[0] = 10;
				simulatedPause(100);
				SharedResource.data[1] = 90;
				simulatedPause(200);
				SharedResource.data[2] = SharedResource.data[0] + SharedResource.data[1];
				simulatedPause(200);
				System.out.println(
						SharedResource.data[0] + " + " + SharedResource.data[1] + " = " + SharedResource.data[2]);
				System.out.println("operation1 after");
			} finally {
				System.out.println("unlocking");
				lock.unlock(stamp);
			}

		}).start();
		
		new Thread(() -> {
			long stamp = -1;
			try {
				stamp = lock.readLock();
				System.out.println("read1 before");
				for (int i = 0; i < 30; i++) {
					simulatedPause(25);
					System.out.println(
							SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
				}
				System.out.println("read1 after");
			} finally {
				System.out.println("unlocking read1");
				lock.unlock(stamp);
			}
		}).start();
		
		new Thread(() -> {
			long stamp = -1;
			try {
				stamp = lock.readLock();
				System.out.println("read2 before");
				for (int i = 0; i < 30; i++) {
					simulatedPause(25);
					System.out.println(
							SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
				}
				System.out.println("read2 after");
			} finally {
				System.out.println("unlocking read2");
				lock.unlock(stamp);
			}
		}).start();

		new Thread(() -> {
			long stamp = -1;
			try {
				stamp = lock.writeLock();
				System.out.println("operation2 before");
				SharedResource.data[0] = 5; 
				simulatedPause(100);
				SharedResource.data[1] = 7;
				simulatedPause(200);
				SharedResource.data[2] = SharedResource.data[0] * SharedResource.data[1]; 
				simulatedPause(200);
				System.out.println(
						SharedResource.data[0] + " * " + SharedResource.data[1] + " = " + SharedResource.data[2]);
				System.out.println("operation2 after");

			} finally {
				System.out.println("unlocking");
				lock.unlock(stamp);
			}
		}).start();
	}

}
