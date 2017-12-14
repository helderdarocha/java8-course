package br.com.argonavis.java.concurrency.synchronizers;

import java.util.Random;
import java.util.concurrent.Semaphore;

interface Resource {
	void method1();
	void method2();
}

class Guardian {
	private static Semaphore permits = new Semaphore(2); // only 2 permits are available each time

	public static SharedResource getResource() {
		try {
			System.out.println(Thread.currentThread().getName() + " trying to get permit (" + permits.availablePermits() + " available)");
			permits.acquire();
			System.out.println(Thread.currentThread().getName() + " got permit. " + permits.availablePermits() + " still available.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SharedResource();
	}
	
	public static void close() {
		System.out.println(Thread.currentThread().getName() + " releasing permit. ");
		permits.release();
		System.out.println(permits.availablePermits() + " still available.");
	}
	
	private static class SharedResource implements Resource {
		public synchronized void method1() {
			System.out.println(Thread.currentThread().getName() + " using shared method1.");
			try { Thread.sleep(new Random().nextInt(1000)); } catch (InterruptedException e) {}
			System.out.println(Thread.currentThread().getName() + " finished using shared method2.");
		}
		
		public synchronized void method2() {
			System.out.println(Thread.currentThread().getName() + " using shared method2.");
			try { Thread.sleep(new Random().nextInt(1000)); } catch (InterruptedException e) {}
			System.out.println(Thread.currentThread().getName() + " finished using shared method1.");
		}
	}
}

public class SemaphoreDemo {
	public static void main(String[] args) {
		
		new Thread(() -> {
			Resource resource = Guardian.getResource();
			resource.method1();
			resource.method2();
			Guardian.close();
		}).start();
		
		new Thread(() -> {
			Resource resource = Guardian.getResource();
			resource.method1();
			resource.method2();
			Guardian.close();
		}).start();
		
		new Thread(() -> {
			Resource resource = Guardian.getResource();
			resource.method1();
			resource.method2();
			Guardian.close();
		}).start();
		
		new Thread(() -> {
			Resource resource = Guardian.getResource();
			resource.method1();
			resource.method2();
			Guardian.close();
		}).start();
		
	}
}