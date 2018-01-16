package br.com.argonavis.java.threads;

public class ThreadExampleAsync5 {
	static class HelloThread extends Thread {
		@Override
		public void run() {
			System.out.println("Hello world from thread " + this.getName());
		}
	}
	
	public static void main(String[] args) {
		new HelloThread().start();
		System.out.println("Thread principal: " + Thread.currentThread().getName());
	}
}
