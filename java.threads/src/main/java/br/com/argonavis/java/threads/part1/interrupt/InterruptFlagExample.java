package br.com.argonavis.java.threads.part1.interrupt;

public class InterruptFlagExample {
	public static void main(String[] args) {
		Runnable runnable = new InterruptRunnable();
        Thread t1 = new Thread(runnable);
        t1.start();

        for(int i = 0; i < 1_000_000; i++) {
        		// loop demorado para atrasar um pouco o thread principal
        }
        
        t1.interrupt(); // sets interrupt flag in t1
        
        System.out.println("Thread " + Thread.currentThread().getName() + " is DONE!"); 
	}
}
