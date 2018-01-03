package br.com.argonavis.java.concurrency.synchronizers;

import java.util.Random;
import java.util.concurrent.Phaser;

import br.com.argonavis.java.concurrency.Utils;

public class PhaserDemo {
	
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3); 
		
		new Thread(() -> {
			Utils.log(" Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			Utils.log(" Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			Utils.log(" Phase " + phaser.getPhase());
			phaser.arriveAndDeregister();
		}).start();
		
		new Thread(() -> {
			Utils.log(" Phase " + phaser.getPhase());
			phaser.arriveAndAwaitAdvance();
			sleep(200);
			Utils.log(" Phase " + phaser.getPhase());
			phaser.arriveAndDeregister();
		}).start();
		
		Utils.log(" Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		sleep(200);
		Utils.log(" Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		sleep(200);
		Utils.log(" Phase " + phaser.getPhase());
		phaser.arriveAndAwaitAdvance();
		phaser.arriveAndDeregister();
		System.out.println("Terminated: " + phaser.isTerminated());
	}
	
	public static void sleep(int maxTime) {
		try { Thread.sleep(new Random().nextInt(maxTime)); } catch (InterruptedException e) {}
	}

}
