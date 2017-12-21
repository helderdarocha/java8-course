package br.com.argonavis.java.threads.part1.wait;

import java.util.Random;

public class WaitExample {

	static volatile boolean producerDone = false;
	static volatile boolean consumerDone = false;

	static class SharedResource {
		private volatile Integer counter = 0;

		public synchronized void produce() {
			if (counter < 30) {
				counter++;
				System.out.print(" " + counter);
				pause(200);
			} else {
				System.out.println("\nPRODUCER is DONE. Counter is full. Notify and leave.");
				producerDone = true;
				this.notify();
			}
		}

		public synchronized void consume() {
			while (counter <= 0 && !producerDone) {
				System.out.println("Nothing to consume. CONSUMER will wait for notification from PRODUCER.");
				try {
					wait();
					System.out.println("CONSUMER thread got lock. Will now start to consume.");
				} catch (InterruptedException e) {
					System.out.println("Wait was interrupted. Will terminate.");
					break;
				}
			}

			if (counter > 0) {
				counter--;
				System.out.print(" " + counter);
				pause(200);
			} else {
				System.out.println("\nCONSUMER is DONE. Counter is empty. Notify and leave.");
				consumerDone = true;
				this.notify();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("MAIN STARTED!");
		SharedResource resource = new SharedResource();

		new Thread(() -> {
			System.out.println("CONSUMER STARTED!");
			while (!consumerDone) {
				resource.consume();
			}
		}).start();
		
		new Thread(() -> {
			System.out.println("PRODUCER STARTED!");
			while (!producerDone) {
				resource.produce();
			}
		}).start();

		synchronized (resource) {
			while (!consumerDone) {
				System.out.println("Nothing to do. MAIN will wait for notification from CONSUMER.");
				try {
					resource.wait();
					System.out.println("MAIN thread got lock. Will now end the thread.");
				} catch (InterruptedException e) {
					System.out.println("Wait was interrupted. Will terminate.");
					break;
				}
			}
			System.out.println("Thread MAIN is DONE!");
		}
	}

	public static void pause(int maxDelay) {
		try {
			Thread.sleep(new Random().nextInt(maxDelay));
		} catch (InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}
}
