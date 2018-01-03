package br.com.argonavis.java.concurrency.synchronizers;

import java.util.concurrent.Semaphore;

import br.com.argonavis.java.concurrency.Utils;

interface Resource {
	static final int PERMITS = 4;
	void method1();
	void method2();
}

class ResourceFactory {
	private Semaphore permits = new Semaphore(Resource.PERMITS); 

	public Resource getResource() {
		try {
			Utils.log(" ~~~ WAITING for permit (" + permits.availablePermits() + " available)");
			permits.acquire();
			Utils.log(" --> GOT permit! " + permits.availablePermits() + " still available.");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} 
		return new Resource() {
			public synchronized void method1() {
				Utils.log("<<< running method1 >>>");
				Utils.simulatedPause(1000);
			}
			
			public synchronized void method2() {
				Utils.log("<<< running method2 >>>");
				Utils.simulatedPause(1000);
			}
		};
	}
	
	public void close() {
		permits.release();
		Utils.log(" /// RELEASING permit. " + permits.availablePermits() + " still available.");
	}
}

class Task implements Runnable {
	private ResourceFactory factory;
	public Task(ResourceFactory factory) {
		this.factory = factory;
	}
	@Override public void run() {
		Utils.simulatedPause(2000);
		Resource resource = factory.getResource();
		resource.method1();
		resource.method2();
		factory.close();
	}
}

public class SemaphoreDemo {
	public static final int THREADS = 8;
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREADS];
		ResourceFactory factory = new ResourceFactory();
		for(int i = 0; i < threads.length; i++) {
		    threads[i] = new Thread(new Task(factory), "Client "+(i+1));
		    threads[i].start();
		}
	}
}