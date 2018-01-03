package br.com.argonavis.java.concurrency.locks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		System.out.println("Hold count before: " + lock.getHoldCount());
		lock.lock();
		try {
			System.out.println("Hold count after lock 1: " + lock.getHoldCount());
			lock.lock();
			try {
				System.out.println("Hold count after lock 2: " + lock.getHoldCount());
				lock.lock();
				try {
					System.out.println("Hold count after lock 3: " + lock.getHoldCount());
				} finally {
					lock.unlock();
					System.out.println("Hold count after unlock 3: " + lock.getHoldCount());
				}
			} finally {
				lock.unlock();
				System.out.println("Hold count after unlock 2: " + lock.getHoldCount());
			}
		} finally {
			lock.unlock();
			System.out.println("Hold count after unlock 1: " + lock.getHoldCount());
		}
	}
}
