package br.com.argonavis.java.concurrency.collections;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class VectorVsCOWAL {
	
	static final int THREADS = 1000;
	static final int SIZE = 1000;
	
	public static void test(Consumer<Integer> f) {
		for(int i = 0; i < SIZE; i++) {
		    f.accept(i);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// Testing Vector
		Thread[] readers = new Thread[THREADS];
		Thread[] writers = new Thread[THREADS];
		List<Integer> vector = new Vector<>(SIZE);
		for(int i = 0; i < SIZE; i++) {
		    vector.add(0);
		}

		long start = System.currentTimeMillis();
		for(int i = 0; i < writers.length; i++) {
			writers[i] = new Thread(() -> test(n->{vector.add(n); vector.remove(0);}));
			writers[i].start();
		}
		for(Thread writer : writers)
			writer.join();
		System.out.println("Writers on Vector: " + (System.currentTimeMillis() - start) + " ms.");
		
		start = System.currentTimeMillis();
		for(int i = 0; i < readers.length; i++) {
			readers[i] = new Thread(() -> test(n->vector.get(n)));
			readers[i].start();
		}
		for(Thread reader : readers)
			reader.join();
		System.out.println("Readers on Vector: " + (System.currentTimeMillis() - start) + " ms.");
		
		// Testing COWAL
		readers = new Thread[THREADS];
		writers = new Thread[THREADS];
		List<Integer> cowal  = new CopyOnWriteArrayList<>(vector);
		
		start = System.currentTimeMillis();
		for(int i = 0; i < writers.length; i++) {
			writers[i] = new Thread(() -> test(n->{cowal.add(n); cowal.remove(0);}));
			writers[i].start();
		}
		for(Thread writer : writers)
			writer.join();
		System.out.println("Writers on COWAL: " + (System.currentTimeMillis() - start) + " ms.");
		
		start = System.currentTimeMillis();
		for(int i = 0; i < readers.length; i++) {
			readers[i] = new Thread(() -> test(n->cowal.get(n)));
			readers[i].start();
		}
		for(Thread reader : readers)
			reader.join();
		System.out.println("Readers on COWAL: " + (System.currentTimeMillis() - start) + " ms.");

	}

}
