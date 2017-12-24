package br.com.argonavis.java.threads.notify;

import java.util.Random;

public class Simulation {
	public static void pause(int maxDelay) {
		try {
			Thread.sleep(new Random().nextInt(maxDelay));
		} catch (InterruptedException e) {}
	}
	public static void delay(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {}
	}
}
