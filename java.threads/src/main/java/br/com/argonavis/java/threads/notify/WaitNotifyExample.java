package br.com.argonavis.java.threads.notify;

public class WaitNotifyExample {

	public static void main(String[] args) {
		Object trava = new Object();
		
		new Thread( new Runnable() {
		    @Override public void run() {
		        synchronized(trava) {
		            System.out.println(Thread.currentThread().getName() + " notificando.");
		            trava.notify();
		        }
		    }
		}).start();   
		
	    	synchronized(trava) {
		    try { trava.wait(); } catch (InterruptedException e) {}
		    System.out.println("Notificação recebida por " + Thread.currentThread().getName());
		}

	}

}
