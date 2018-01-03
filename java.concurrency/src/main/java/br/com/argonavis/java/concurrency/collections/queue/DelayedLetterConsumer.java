package br.com.argonavis.java.concurrency.collections.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class DelayedLetterConsumer implements Runnable {
	private final BlockingQueue<DelayedLetter> queue;
	private String name;

	DelayedLetterConsumer(BlockingQueue<DelayedLetter> q, String name) {
		queue = q;
		this.name = name;
	}

	public void run() { 
		while (true) {
			try {
				DelayedLetter letter = queue.take();
				System.out.println(">>> Consumed by "+name+": " + letter);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		System.out.println("Consumer "+name+" DONE");
	}
}
