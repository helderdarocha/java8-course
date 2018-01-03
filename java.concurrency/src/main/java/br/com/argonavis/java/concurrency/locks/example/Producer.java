package br.com.argonavis.java.concurrency.locks.example;

import java.util.Random;

public class Producer  implements Runnable {
	private SharedObject shared;
	private static final int ATTEPMTS = 3;

	Producer(SharedObject shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		for (int i = 0; i < ATTEPMTS; i++) {
			System.out.println(Thread.currentThread().getName() + ": Attempt no. " + (i+1));
			if(!shared.set(new Random().nextInt(1000))) {
				break; // end thread
			}
		}
		System.out.println(Thread.currentThread().getName() + ": Producer DONE.");
	}
}
