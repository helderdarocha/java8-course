package br.com.argonavis.java.concurrency.executors;

import java.util.Random;

public class ExclusiveTask implements Runnable {
	@Override
	public void run() {
		try { Thread.sleep(new Random().nextInt(1000)); } catch (InterruptedException e) {}
		System.out.println("Exclusive task finished.");
	}
}
