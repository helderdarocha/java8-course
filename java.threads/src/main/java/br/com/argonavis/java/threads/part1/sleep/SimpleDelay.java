package br.com.argonavis.java.threads.part1.sleep;

public class SimpleDelay implements Runnable {
    int delay;
    
    public SimpleDelay(int delay) {
        this.delay = delay;
    }
    
    @Override 
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started.");
        
        try { 
        	    Thread.sleep(delay); 
        	} catch (InterruptedException ignored) {}
        
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}
