package br.com.argonavis.java.concurrency.executors;

import java.util.Random;

public class ConcurrentTask implements Runnable {
	private String name;
	public ConcurrentTask(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		try { Thread.sleep(new Random().nextInt(500)); } catch (InterruptedException e) {}
		System.out.println("Finished: " + name);
	}
}