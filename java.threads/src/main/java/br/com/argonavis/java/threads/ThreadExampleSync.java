package br.com.argonavis.java.threads;

public class ThreadExampleSync {
	public static void main(String[] args) {
		System.out.println("Thread principal: " + Thread.currentThread().getName());
		Runnable paralelo = new RunnableHelloWorld();
		paralelo.run();
	}
}
