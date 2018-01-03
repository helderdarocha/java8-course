package br.com.argonavis.java.concurrency;

import java.util.Random;

public class Utils {
	public static void simulatedPause(int maxTime) {
		try { 
			Thread.sleep(new Random().nextInt(maxTime)); 
		} catch (InterruptedException ignored) {
			Thread.currentThread().interrupt();
		}
	}
	 
	public static void begin(String name) {
		System.out.println(Thread.currentThread().getName() + " begin " + name);
	}

	public static void end(String name) {
		System.out.println(Thread.currentThread().getName() + " end " + name);
	}
	
	public static void log(String name) {
		System.out.println(Thread.currentThread().getName() + " " + name);
	}

}
