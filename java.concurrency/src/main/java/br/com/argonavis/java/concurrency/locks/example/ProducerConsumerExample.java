package br.com.argonavis.java.concurrency.locks.example;

public class ProducerConsumerExample {

	public static void main(String[] args) {
        SharedObject o = new SharedObject();
        String[] names = {"C1", "C2", "P1", "P2"};
        Thread[] threads = { new Thread(new Consumer(o)), new Thread(new Consumer(o)), 
                             new Thread(new Producer(o)), new Thread(new Producer(o)) };
		
        for(int i = 0; i < threads.length; i++) {
            threads[i].setName(names[i]);
            threads[i].start();
        }
		
        for(Thread t: threads) {
            try {
                t.join(15000); // will wait up to 15 seconds for each thread to finish
                if(t.isAlive()) { t.interrupt(); }
		} catch (InterruptedException ignored) {}
	    }
        System.out.println("Main DONE.");
	}
}
