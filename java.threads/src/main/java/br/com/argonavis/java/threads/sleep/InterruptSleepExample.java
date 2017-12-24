package br.com.argonavis.java.threads.sleep;

public class InterruptSleepExample {
	public static void main(String[] args) {
		Runnable runnable = new RandomLetters();
        Thread t1 = new Thread(runnable);
        t1.start();

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        
        t1.interrupt(); // sets interrupt flag in t1
        
        System.out.println("\nThread " + Thread.currentThread().getName() + " is DONE!");
	}
}
