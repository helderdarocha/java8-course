package br.com.argonavis.java.threads.notify;

public class WaitExample {

	public static void main(String[] args) {
		SharedObject object = new SharedObject();
		String[] names = {"C1", "C2", "P1", "P2"};
		Thread[] threads = {
				new Thread(new Consumer(object)), 
				new Thread(new Consumer(object)), 
				new Thread(new Producer(object)), 
				new Thread(new Producer(object))
			};
		
		for(int i = 0; i < threads.length; i++) {
			threads[i].setName(names[i]);
			threads[i].start();
		}
		
		for(Thread t: threads) {
			try {
				t.join(15000); // will wait up to 15 seconds for each thread to finish
				System.out.println("Thread " + t.getName() + " joined MAIN.");
				if(t.isAlive()) {
					System.out.println("Will interrupt " + t.getName()); 
					t.interrupt();
				}
			} catch (InterruptedException e) {
				System.out.println("MAIN: thread interrupted.");
			}
		}
		
		System.out.println("Main DONE.");
	}

}
