package br.com.argonavis.java.concurrency.atomic.cas;

public class VolatileSynchronizedCounter {
	private volatile int value;
	
	public synchronized void add(int value) {
		this.value += value;
	}

	public synchronized void increment() {
		this.value++;
	}

	public synchronized void decrement() {
		this.value--;
	}

	public void set(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}
}
