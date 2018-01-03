package br.com.argonavis.java.concurrency.collections.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class Producer implements Runnable {
	private int[] data = IntStream.range('A', 'A'+26).toArray();
	
	private final BlockingQueue<String> queue;

	Producer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() { 
		for(int c : data) {
			try {
				String letter = "" + (char)c;
				queue.put(letter);
				System.out.println("<<< Produced: " + letter);
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		System.out.println("Producer DONE");
		
	}
}