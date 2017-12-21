package br.com.argonavis.java.threads.other;

/**
 * This example only works in single-core computers running preemptive scheduling operating systems.
 * Avoid using Thread.yield() and thread priorities.
 */
public class ThreadPriorityExample {

	public static void main(String[] args) {
		PriorityRunner r1 = new PriorityRunner("da montanha");
		Thread carneiros1 = new Thread(r1, "Thread 1");
		
		PriorityRunner r2 = new PriorityRunner("do campo");
		Thread carneiros2 = new Thread(r2, "Thread 2");
		
		carneiros2.setPriority(Thread.MAX_PRIORITY);
		carneiros2.setPriority(Thread.MIN_PRIORITY);
		
		carneiros1.start();
		carneiros2.start();
		
		
		

	}

}
