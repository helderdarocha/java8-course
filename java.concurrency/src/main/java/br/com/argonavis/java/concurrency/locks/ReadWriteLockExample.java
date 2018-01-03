package br.com.argonavis.java.concurrency.locks;

import static br.com.argonavis.java.concurrency.Utils.simulatedPause;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		
		new Thread(() -> {
			if (lock.readLock().tryLock()) {
				try {
					System.out.println("read1 before");
					for (int i = 0; i < 30; i++) {
						simulatedPause(25);
						System.out.println(
								SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
					}
					System.out.println("read1 after");
				} finally {
					System.out.println("unlocking read1");
					lock.readLock().unlock();
				}
			} else {
				System.out.println("Read try-lock1 not obtained!");
			}
		}).start();
		
		new Thread(() -> {
			try {
				lock.writeLock().lock();
				System.out.println("write1 before");
				SharedResource.data[0] = 10;
				simulatedPause(100);
				SharedResource.data[1] = 90;
				simulatedPause(200);
				SharedResource.data[2] = SharedResource.data[0] + SharedResource.data[1];
				simulatedPause(200);
				System.out.println(
						SharedResource.data[0] + " + " + SharedResource.data[1] + " = " + SharedResource.data[2]);
				System.out.println("write1 after");
			} finally {
				System.out.println("unlocking write1");
				lock.writeLock().unlock();
			}

		}).start();
		
		new Thread(() -> {
			lock.readLock().lock();
			try {
				System.out.println("read2 before");
				for (int i = 0; i < 30; i++) {
					simulatedPause(25);
					System.out.println(
							SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
				}
				System.out.println("read2 after");
			} finally {
				System.out.println("unlocking read2");
				lock.readLock().unlock();
			}
		}).start();
		
		new Thread(() -> {
			try {
				lock.writeLock().lock();
				System.out.println("write2 before");
				SharedResource.data[0] = 5;
				simulatedPause(100);
				SharedResource.data[1] = 7;
				simulatedPause(200);
				SharedResource.data[2] = SharedResource.data[0] * SharedResource.data[1];
				simulatedPause(200);
				System.out.println(
						SharedResource.data[0] + " * " + SharedResource.data[1] + " = " + SharedResource.data[2]);
				System.out.println("write2 after");

			} finally {
				System.out.println("unlocking write2");
				lock.writeLock().unlock();
			}
		}).start();
		
		new Thread(() -> {
			simulatedPause(3);
			if (lock.readLock().tryLock()) {
				try {
					System.out.println("read3 before");
					for (int i = 0; i < 30; i++) {
						simulatedPause(25);
						System.out.println(
								SharedResource.data[0] + ", " + SharedResource.data[1] + ", " + SharedResource.data[2]);
					}
					System.out.println("read3 after");
				} finally {
					System.out.println("unlocking read3");
					lock.readLock().unlock();
				}
			} else {
				System.out.println("Read try-lock2 not available!");
			}
		}).start();

	}

}
