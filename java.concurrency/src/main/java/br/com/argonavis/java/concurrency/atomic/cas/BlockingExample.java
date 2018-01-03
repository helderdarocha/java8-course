package br.com.argonavis.java.concurrency.atomic.cas;

public class BlockingExample {

	public static void main(String[] args) {
		System.out.println("** UnsafeCounter **");
		UnsafeCounter unsafe = new UnsafeCounter();
		unsafe.set(100);
		System.out.println(unsafe.get());
	    unsafe.add(20);
	    System.out.println(unsafe.get());
	    unsafe.increment();
	    System.out.println(unsafe.get());
	    unsafe.decrement();
	    System.out.println(unsafe.get());
	    
	    System.out.println("\n** SynchronizedCounter **");
		SynchronizedCounter sync = new SynchronizedCounter();
		sync.set(100);
		System.out.println(sync.get());
		sync.add(20);
	    System.out.println(sync.get());
	    sync.increment();
	    System.out.println(sync.get());
	    sync.decrement();
	    System.out.println(sync.get());
	    
	    System.out.println("\n** VolatileSynchronizedCounter **");
		VolatileSynchronizedCounter volsync = new VolatileSynchronizedCounter();
		volsync.set(100);
		System.out.println(volsync.get());
		volsync.add(20);
	    System.out.println(volsync.get());
	    volsync.increment();
	    System.out.println(volsync.get());
	    volsync.decrement();
	    System.out.println(volsync.get());
	    
	    System.out.println("\n** VolatileCASCounter **");
		VolatileCASCounter volcas = new VolatileCASCounter();
		volcas.set(100);
		System.out.println(volcas.get());
		volcas.add(20);
	    System.out.println(volcas.get());
	    volcas.increment();
	    System.out.println(volcas.get());
	    volcas.decrement();
	    System.out.println(volcas.get());
	    
	    System.out.println("\n** AtomicCASCounter **");
		AtomicCASCounter atocas = new AtomicCASCounter();
		atocas.set(100);
		System.out.println(atocas.get());
		atocas.add(20);
	    System.out.println(atocas.get());
	    atocas.increment();
	    System.out.println(atocas.get());
	    atocas.decrement();
	    System.out.println(atocas.get());

	}

}
