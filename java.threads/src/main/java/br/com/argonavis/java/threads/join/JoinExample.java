package br.com.argonavis.java.threads.join;

import br.com.argonavis.java.threads.sleep.SimpleDelay;

public class JoinExample {
    public static void main(String[] args) {
    	
        Thread t1 = new Thread(new SimpleDelay(2000));
        t1.start();
        Thread t2 = new Thread(new SimpleDelay(5000));
        t2.start();
        
        System.out.println("Waiting for " + t1.getName());
        try {
			t1.join();
		} catch (InterruptedException e) {
			System.out.println(t1.getName() + " was interrupted before finishing task.");
		}
        System.out.println("Waiting for " + t2.getName());
        try {
			t2.join();
        } catch (InterruptedException e) {
			System.out.println(t2.getName() + " was interrupted before finishing task.");
		}
        System.out.println("Thread main is DONE!");
    }
}
