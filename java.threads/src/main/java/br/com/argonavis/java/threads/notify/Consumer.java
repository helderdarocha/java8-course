package br.com.argonavis.java.threads.notify;

public class Consumer implements Runnable {
	private SharedObject shared;
	private static final int ATTEPMTS = 3;

	Consumer(SharedObject shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		for (int i = 0; i < ATTEPMTS; i++) {
			System.out.println(Thread.currentThread().getName() + ": Attempt no. " + (i+1));
			if(!shared.reset()) {
				break; // end thread
			}
		}
		System.out.println(Thread.currentThread().getName() + ": Consumer DONE.");
	}
}