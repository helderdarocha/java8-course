package br.com.argonavis.java.threads.synch;

public class HoldsLockExample {
	static class SharedResource {
		public synchronized void synchronizedMethod() {
			System.out.println("synchronizedMethod(): " + Thread.holdsLock(this));
		}

		public void method() {
			System.out.println("method(): " + Thread.holdsLock(this));
		}
	}

	public static void main(String[] args) throws InterruptedException {
		SharedResource obj = new SharedResource();

		synchronized (obj) {
			obj.method();
		}
		obj.method();
		obj.synchronizedMethod();
	}
}
