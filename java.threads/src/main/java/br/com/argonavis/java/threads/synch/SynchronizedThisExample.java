package br.com.argonavis.java.threads.synch;

import java.util.Random;

public class SynchronizedThisExample {

	static class WordBuffer {
		// Variável compartilhada entre threads
		static StringBuilder buffer = new StringBuilder();

		public synchronized String buildAndPrint(String prefix, String text, String suffix) {
			buffer.setLength(0);
			buffer.append(prefix);
			pause(200);
			buffer.append(text);
			pause(200);
			buffer.append(suffix);
			return buffer.toString();
		}
	}

	public static void main(String[] args) {
		WordBuffer wb = new WordBuffer();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(wb.buildAndPrint("ONE", "argument", "TWO"));
			}
		}).start();

		new Thread(() -> System.out.println(wb.buildAndPrint("X-", "0", "-Y"))).start();

		System.out.println(wb.buildAndPrint("000.", "111", ".999"));
	}

	public static void pause(int maxDelay) {
		try {Thread.sleep(new Random().nextInt(maxDelay));} catch (InterruptedException e) {}
	}
}
