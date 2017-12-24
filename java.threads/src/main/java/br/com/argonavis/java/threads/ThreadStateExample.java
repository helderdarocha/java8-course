package br.com.argonavis.java.threads;

public class ThreadStateExample {

	public static void main(String[] args) throws InterruptedException {
		Object log = new Object();
		Thread main = Thread.currentThread();

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("2. " + Thread.currentThread().getName() + ": " + Thread.currentThread().getState());
				try {
					synchronized (log) {
						log.notify();
					}
					Thread.sleep(2000); // TIMED_WAITING
					synchronized (log) {
						log.wait(); // BLOCKED
					}
				} catch (InterruptedException e) {
					System.out.println("5. " + main.getName() + ": " + main.getState());
					return;
				}
			}
		};

		Thread t = new Thread(runnable);
		System.out.println("1. " + t.getName() + ": " + t.getState()); // NEW
		t.start(); // RUNNABLE

		synchronized (log) {
			try {
				log.wait();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getState());
			}
			System.out.println("3. " + t.getName() + ": " + t.getState());
			Thread.sleep(2000);
			System.out.println("4. " + t.getName() + ": " + t.getState());
		}

		t.interrupt();
		System.out.println("5. " + t.getName() + ": " + t.getState());
		t.join(); // WAITING
		System.out.println("6. " + t.getName() + ": " + t.getState());
		// TERMINATED
	}

}
