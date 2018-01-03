package br.com.argonavis.java.concurrency.atomic.cas;

public class UnsafeCounter {
	private int value;
	
	public void add(int value) {
		this.value += value;
	}

	public void increment() {
		this.value++;
	}

	public void decrement() {
		this.value--;
	}

	public void set(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}
}
