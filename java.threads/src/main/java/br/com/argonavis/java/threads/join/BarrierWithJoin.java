package br.com.argonavis.java.threads.join;

public class BarrierWithJoin {
	Thread[] threads;
	
	public BarrierWithJoin(Thread... threads) {
		this.threads = threads;
	}
	
	public void startThreads() {
		for(Thread t: threads) {
			t.start();
		}
	}
	public void waitForThreads() {
		for(Thread t: threads) {
			try {
				t.join(); // will join main thread when finished
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
