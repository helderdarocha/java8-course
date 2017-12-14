package br.com.argonavis.java.threads;

public class ThreadTest2 {

	public static void main(String[] args) {
		Paralelo r1 = new Paralelo("da montanha", 500);
		Thread carneiros1 = new Thread(r1, "Thread 1");
		
		Paralelo r2 = new Paralelo("do campo", 250);
		Thread carneiros2 = new Thread(r2, "Thread 2");
		
		carneiros1.start();
		
		carneiros2.start();
		
		Thread mainThread = Thread.currentThread();
		mainThread.setName("Main Thread");
		
		try {
			carneiros1.join();
			System.out.println(carneiros1.toString() + " dead.");
			carneiros2.join();
			System.out.println(carneiros2.toString() + " dead.");
		} catch (InterruptedException e) {}
		
		System.out.println(mainThread.toString() + " dead.");
	}

}
