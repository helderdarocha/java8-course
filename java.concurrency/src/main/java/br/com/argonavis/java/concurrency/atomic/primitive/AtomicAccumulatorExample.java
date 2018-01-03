package br.com.argonavis.java.concurrency.atomic.primitive;

import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.LongStream;

public class AtomicAccumulatorExample {

	public static void main(String[] args) {
		
		System.out.println("*** LongAccumulator ***");
		LongAccumulator longAcc = new LongAccumulator((acc, n) -> acc + n, 0);
		LongStream.of(13, 23, 34, 33, 22, 76).forEach(i -> longAcc.accumulate(i));
		System.out.println("Result: " + longAcc.get()); // same as longValue()
		
		System.out.println("\n*** DoubleAccumulator ***"); 
		DoubleAccumulator doubleAcc = new DoubleAccumulator((acc, n) -> acc + n, 0);
		LongStream.of(13, 23, 34, 33, 22, 76).forEach(i -> doubleAcc.accumulate(i));
		System.out.println("Result: " + doubleAcc.get()); // same as longValue()
	}
}
