package br.com.argonavis.java.concurrency.atomic.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Using only the primitives: get, set, compareAndSet
 *
 */
public class AtomicCASCounter {
	private AtomicInteger object = new AtomicInteger(0);
	
	public void add(int added) {
		int current = object.get();
		while(!object.compareAndSet(current, current+added)) {
			current = object.get();
		}
	}

	public void increment() {
		int current = object.get();
		while(!object.compareAndSet(current, current+1)) {
			current = object.get();
		}
	}

	public void decrement() {
		int current = object.get();
		while(!object.compareAndSet(current, current-1)) {
			current = object.get();
		}
	}

	public void set(int value) {
		object.set(value);
	}

	public int get() {
		return object.get();
	}
}
