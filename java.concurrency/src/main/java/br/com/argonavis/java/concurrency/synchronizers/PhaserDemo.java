package br.com.argonavis.java.concurrency.synchronizers;

import java.util.Random;
import java.util.concurrent.Phaser;

public class PhaserDemo {
	
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		
		new Thread(() -> {
			System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
			phaser.arriveAndDeregister();
		}).start();
		
		new Thread(() -> {
			System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
			phaser.arriveAndDeregister();
		}).start();
		
		System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		sleep(200);
		System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		sleep(200);
		System.out.println("Thread " + Thread.currentThread().getName() + ", Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		phaser.arriveAndDeregister();
		System.out.println("Terminated: " + phaser.isTerminated());
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}

}
