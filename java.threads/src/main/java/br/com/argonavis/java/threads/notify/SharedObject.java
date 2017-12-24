package br.com.argonavis.java.threads.notify;

/**
 * 
 * Experimente trocar notify() por notifyAll() e veja a diferença.
 *
 */
public class SharedObject {
	private volatile int value = -1;

	public boolean isSet() {
		return value != -1;
	}

	public synchronized boolean set(int v) {
		if (!isSet()) {
			Simulation.delay(2000); // pretend this operation takes long (so you can see it working)
			value = v;
			System.out.println(Thread.currentThread().getName() + ": PRODUCED: " + value + ". Will notify.");
			notifyAll(); // avisa a todos os threads
			//notify(); // avisa a um thread que espera no objeto (pode ser outro produtor, que não vai poder operar)
		} else {
			System.out.println(Thread.currentThread().getName() + ": Can't produce. Value already set (" + value + "). Will wait. Release lock.");
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + ": Got lock.");
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Interrupted. Will terminate producer.");
				return false;
			}
		}
		return true;
	}

	public synchronized boolean reset() {
		if (isSet()) {
			Simulation.delay(2000); // pretend this operation takes long (so you can see it working)
			System.out.println(Thread.currentThread().getName() + ": CONSUMED: " + value + ". Will notify.");
			value = -1;
			notifyAll(); // avisa a todos os threads
			//notify(); // avisa a um thread que espera no objeto (pode ser outro consumidor, que não vai poder operar)
		} else {
			System.out.println(Thread.currentThread().getName() + ": Nothing to consume. Will wait. Release lock.");
			try {
				wait();
				System.out.println(Thread.currentThread().getName() + ": Got lock.");
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + ": Interrupted. Will terminate consumer.");
				return false;
			}
		}
		return true;
	}
}