package br.com.argonavis.java.threads.interrupt;

public class InterruptRunnable implements Runnable {
	@Override
	public void run() {
		boolean interrupt = false;
		while(!interrupt) {
			interrupt = Thread.interrupted();
			System.out.println(">INTERRUPTED flag: " + interrupt);
		}
		System.out.println("INTERRUPTED flag: " + interrupt);
		System.out.println("Thread " + Thread.currentThread().getName() + " is DONE!");
	}
}
