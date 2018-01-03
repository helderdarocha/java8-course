package br.com.argonavis.java.concurrency.atomic.primitive;

import java.util.Date;

public class AtomicDateExample {

	public static void main(String[] args) {
		Thread[] threads = new Thread[3];
		AtomicDate date = new AtomicDate();
		date.set(new Date());
		
		System.out.println("Today: " + date.get());
		System.out.println("Tomorrow: " + date.tomorrow());
		System.out.println("Today: " + date.yesterday());
		
		// Days depend on order of calls, but date + increment operation is atomic
		threads[0] = new Thread(() -> System.out.println("Some day: " + date.tomorrow()));
		threads[1] = new Thread(() -> System.out.println("Some day: " + date.yesterday()));
		threads[2] = new Thread(() -> System.out.println("Some day: " + date.yesterday()));
		
		for(Thread t: threads)
			t.start();
	}
}
