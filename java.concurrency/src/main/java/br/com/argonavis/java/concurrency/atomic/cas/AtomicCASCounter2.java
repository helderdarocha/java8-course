package br.com.argonavis.java.concurrency.atomic.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Using AtomicInteger methods
 *
 */
public class AtomicCASCounter2 {
	private AtomicInteger object = new AtomicInteger(0);
	
	public void add(int added) {
		object.addAndGet(added);
	}

	public void increment() {
		object.getAndIncrement();
	}

	public void decrement() {
		object.getAndDecrement();
	}

	public void set(int value) {
		object.set(value);
	}

	public int get() {
		return object.get();
	}
}
