package br.com.argonavis.java.concurrency.locks.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedObject {
	private final Lock lock = new ReentrantLock();
	private final Condition canWrite = lock.newCondition();
	private final Condition mayRead = lock.newCondition();
	private volatile int value = -1;

	public boolean isSet() {
		return value != -1;
	}

	public boolean set(int v) {
		lock.lock();
		try {
			while (isSet()) {
				System.out.println(Thread.currentThread().getName() + ": Can't produce. Value already set (" + value
						+ "). Will wait. Release lock.");
				try {
					canWrite.await(); // wait for writing permission
					System.out.println(Thread.currentThread().getName() + ": Got lock.");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + ": Interrupted. Will terminate producer.");
					return false;
				}
			}
			Simulation.delay(2000); // pretend this operation takes long (so you can see it working)
			value = v;
			System.out.println(Thread.currentThread().getName() + ": PRODUCED: " + value + ".");
			mayRead.signal(); // Signal to readers
		} finally {
			lock.unlock();
		}
		return true;
	}

	public boolean reset() {
		lock.lock();
		try {
			while (!isSet()) {
				System.out.println(Thread.currentThread().getName() + ": Nothing to consume. Will wait. Release lock.");
				try {
					mayRead.await(); // wait for permission to read
					System.out.println(Thread.currentThread().getName() + ": Got lock.");
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + ": Interrupted. Will terminate consumer.");
					return false;
				}
			}
			Simulation.delay(2000); // pretend this operation takes long (so you can see it working)
			System.out.println(Thread.currentThread().getName() + ": CONSUMED: " + value + ".");
			value = -1;
			canWrite.signal(); // Signal to writers
		} finally {
			lock.unlock();
		}
		return true;
	}
}