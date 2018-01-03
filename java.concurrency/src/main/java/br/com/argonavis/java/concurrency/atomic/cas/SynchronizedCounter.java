package br.com.argonavis.java.concurrency.atomic.cas;

public class SynchronizedCounter {
	private int value;
	
	public synchronized void add(int value) {
		this.value += value;
	}

	public synchronized void increment() {
		this.value++;
	}

	public synchronized void decrement() {
		this.value--;
	}

	public synchronized void set(int value) {
		this.value = value;
	}

	public synchronized int get() {
		return value;
	}
}
