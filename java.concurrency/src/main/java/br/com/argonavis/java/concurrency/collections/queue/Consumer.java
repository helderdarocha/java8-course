package br.com.argonavis.java.concurrency.collections.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Consumer implements Runnable {
	private final BlockingQueue<String> queue;
	private String name;

	Consumer(BlockingQueue<String> q, String name) {
		queue = q;
		this.name = name;
	}

	public void run() { 
		while (true) {
			try {
				System.out.println(">>> Consumed by "+name+": " + queue.take());
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		System.out.println("Consumer "+name+" DONE");
	}
}
