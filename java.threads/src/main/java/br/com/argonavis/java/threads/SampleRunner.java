package br.com.argonavis.java.threads;

public class SampleRunner implements Runnable {
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println(Thread.currentThread().getName() + " running.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				break;
			}
		}
		System.out.println(Thread.currentThread().getName() + " DONE!");
	}
}