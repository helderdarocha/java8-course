package br.com.argonavis.java.concurrency.atomic.cas;

public class VolatileCASCounter {
	private volatile int value;
	
	private synchronized boolean compareAndSet(int expected, int newValue) {
        if (expected == this.value) {
            this.value = newValue;
            return true;
        }
        return false;
	}
	
	public void add(int added) {
		int oldValue = this.value;
		while(!compareAndSet(oldValue, oldValue + added)) {
			oldValue = this.value;
		}
	}

	public void increment() {
		this.add(1);
	}

	public void decrement() {
		this.add(-1);
	}

	public void set(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}
}
