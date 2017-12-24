package br.com.argonavis.java.threads;

import java.util.Random;

public class ThreadLocalExample2 {
	
	static ThreadLocal<StringBuffer> localString = new ThreadLocal<>() {
		@Override
		public StringBuffer initialValue() {
			return new StringBuffer();
		}
	};
	
	static ThreadLocal<Integer> localInteger = ThreadLocal.withInitial(() -> new Random().nextInt(1000));
	
	static Runnable wordGenerator = new Runnable() {
		@Override public void run() {
			for(int i = 0; i < 10; i++) {
				localString.get().append((char)(new Random().nextInt(26) + 'A'));
			}
			System.out.println(localString.get());
		}
	};

	public static void main(String[] args) {
		new Thread(wordGenerator).start();
		new Thread(wordGenerator).start();
		new Thread(wordGenerator).start();
		new Thread(wordGenerator).start();
		new Thread(wordGenerator).start();
		new Thread(wordGenerator).start();
		
		wordGenerator.run();
		
		System.out.println("Main: " + localInteger.get());
		System.out.println("Main: " + localInteger.get());
		new Thread(()->System.out.println("T1: " + localInteger.get())).start();
		new Thread(()->System.out.println("T2: " + localInteger.get())).start();
	}
}
