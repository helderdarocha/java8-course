package br.com.argonavis.java.concurrency.atomic.primitive;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.LongStream;

public class AtomicAdderExample {

	public static void main(String[] args) {
		DoubleAdder doubleAdder = new DoubleAdder();
		LongAdder longAdder = new LongAdder();
		
		System.out.println("*** LongAdder ***");
		longAdder.add(100);
		System.out.println("After add 100: " + longAdder.longValue()); // same as sum()
		longAdder.reset(); 
		System.out.println("After reset: " + longAdder.longValue()); // same as sum()
		longAdder.decrement();
		System.out.println("After decrement: " + longAdder.longValue()); // same as sum()
		longAdder.increment();
		System.out.println("After increment: " + longAdder.sum()); 
		longAdder.add(150);
		System.out.println("After add: " + longAdder.sumThenReset()); 
		System.out.println("After sumThenReset: " + longAdder.longValue()); // same as sum()
		LongStream.of(13, 23, 34, 33, 22, 76).forEach(longAdder::add);
		System.out.println("After stream adding: " + longAdder.longValue()); // same as sum()
		
		System.out.println("\n*** DoubleAdder ***"); 
		doubleAdder.add(123.45);
		doubleAdder.add(937.32);
		System.out.println("After add: " + doubleAdder.sumThenReset()); 
		System.out.println("After sumThenReset: " + doubleAdder.longValue()); // same as sum()
		
		
		
	}

}
