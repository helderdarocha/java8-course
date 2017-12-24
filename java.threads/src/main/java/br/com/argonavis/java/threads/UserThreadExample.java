package br.com.argonavis.java.threads;

public class UserThreadExample {

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[2];
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new SampleRunner());
		    //threads[i].setDaemon(true); 
		    threads[i].start();
		}
		Thread.sleep(1000); // depois deste intervalo main termina
		System.out.println(Thread.currentThread().getName() + " DONE!");
	}

}
