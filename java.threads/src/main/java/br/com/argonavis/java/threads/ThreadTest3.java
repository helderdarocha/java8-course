package br.com.argonavis.java.threads;

public class ThreadTest3 {

	public static void main(String[] args) {
		PriorityParalelo r1 = new PriorityParalelo("da montanha");
		Thread carneiros1 = new Thread(r1, "Thread 1");
		
		PriorityParalelo r2 = new PriorityParalelo("do campo");
		Thread carneiros2 = new Thread(r2, "Thread 2");
		
		carneiros2.setPriority(Thread.MAX_PRIORITY);
		carneiros2.setPriority(Thread.MIN_PRIORITY);
		
		carneiros1.start();
		carneiros2.start();
		
		
		

	}

}
