package br.com.argonavis.java.concurrency.atomic.primitive;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class AtomicPrimitiveExample {

	public static void main(String[] args) {
		AtomicBoolean astate = new AtomicBoolean(true);
		AtomicInteger acount = new AtomicInteger(100);
		AtomicLong atimestamp = new AtomicLong(Instant.now().toEpochMilli());
		
		System.out.println("*** AtomicBoolean ***");
		System.out.println("Stored value: " + astate.get());
		System.out.println("Setting to false");
		astate.set(false);
		boolean result1 = astate.compareAndSet(false, true); // if false, set to true
		System.out.println("New result was " + (result1 ? "set." : "not set."));
		System.out.println("Previous value: " + astate.getAndSet(false));
		System.out.println("New value: " + astate.get());
		
		System.out.println("\n*** AtomicInteger ***");
		System.out.println("Stored value: " + acount.get());
		System.out.println("Setting to -156");
		acount.set(-156);
		boolean result2 = acount.compareAndSet(-156, 1000); // if 156, set to 1000
		System.out.println("New result was " + (result2 ? "set." : "not set."));
		System.out.println("Previous value: " + acount.getAndSet(876));
		System.out.println("New value: " + acount.get());
		System.out.println("Increment: " + acount.incrementAndGet());
		System.out.println("Added 234: " + acount.addAndGet(234));
		System.out.println("Decrement: " + acount.decrementAndGet());
		System.out.println("Get, then increment: " + acount.getAndIncrement());
		System.out.println("Get, then decrement: " + acount.getAndIncrement());
		System.out.println("Final value: " + acount.get());
		
		System.out.println("\n*** AtomicLong ***");
		System.out.println("Stored value: " + atimestamp.get());
		System.out.println("Previous value: " + atimestamp.getAndSet(100)); // get and replace
		System.out.println("New value: " + atimestamp.get());
		LongStream.range(0, 10).map(i -> atimestamp.getAndUpdate(n -> n+1)).forEach(n->System.out.print(" " + n));
		System.out.println("\nUpdated value: " + atimestamp.get());
		LongStream.range(0, 10).map(i -> atimestamp.accumulateAndGet(i, (acc, n) -> acc + n)).forEach(n->System.out.print(" " + n));;
		System.out.println("\nAccumulated value: " + atimestamp.get());

	}

}
