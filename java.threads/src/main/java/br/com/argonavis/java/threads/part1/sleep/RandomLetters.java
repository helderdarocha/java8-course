package br.com.argonavis.java.threads.part1.sleep;

import java.util.Random;

public class RandomLetters implements Runnable {
	@Override
	public void run() {
		while(true) {
			System.out.print(" " + (char)('A' + new Random().nextInt(26)));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("\n" + Thread.currentThread().getName() + " was interrupted. ");
				System.out.println("INTERRUPTED flag is now: " + Thread.currentThread().isInterrupted()); 
				break; // terminate the thread
			}
		}
		System.out.println("Thread " + Thread.currentThread().getName() + " is DONE!");
	}
}
