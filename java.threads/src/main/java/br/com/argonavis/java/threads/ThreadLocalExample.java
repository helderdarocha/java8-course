package br.com.argonavis.java.threads;

public class ThreadLocalExample {
	
	static ThreadLocal<Integer> tloc = new ThreadLocal<>();
	
	public static void main(String[] args) throws InterruptedException {
		tloc.set(123); // setting value local for thread main
		System.out.println(Thread.currentThread().getName() 
                            + " sees tloc = " + tloc.get());
		
		Thread t1 = new Thread(() -> { // Thread-0 will read initialValue()
			System.out.println(Thread.currentThread().getName() 
					            + " sees tloc = " + tloc.get());
		});	                                        
		t1.start();
		
		Thread t2 = new Thread(() -> {
			tloc.set(456); // setting value for Thread-1
			System.out.println(Thread.currentThread().getName() 
                                 + " sees tloc = " + tloc.get());
		});
		t2.start();
		
		t1.join();
		t2.join();
		
		// Value unchanged for thread main
		System.out.println(Thread.currentThread().getName() 
                            + " sees tloc = " + tloc.get());
	}
}
