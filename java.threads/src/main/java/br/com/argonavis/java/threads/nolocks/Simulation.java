package br.com.argonavis.java.threads.nolocks;

import java.util.Random;

public class Simulation {
	public static void pause(int maxDelay) {
		try {
			Thread.sleep(new Random().nextInt(maxDelay));
		} catch (InterruptedException e) {}
	}
}
