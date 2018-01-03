package br.com.argonavis.java.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class AtomicArrayExample {

	public static void main(String[] args) {
		System.out.println("*** AtomicIntegerArray ***");
		AtomicIntegerArray intArray = new AtomicIntegerArray(3);
		intArray.set(0, 12);
		intArray.set(1, 36);
		intArray.set(2, 76);
		System.out.println("Array: " + intArray);
		
		intArray.addAndGet(0, 5); // add 5 to array[0]
		intArray.compareAndExchange(1, 36, 45); // if array[1] == 36, replace with 45
		System.out.println("Old value at [2]: " + intArray.getAndSet(2, 72));
		System.out.println("Array: " + intArray);
		
		System.out.println("\n*** AtomicLongArray ***");
		AtomicLongArray longArray = new AtomicLongArray(new long[] {34L, 12L, 78L, 99L});
		System.out.println("Array: " + longArray);
		longArray.addAndGet(0, 5); // add 5 to array[0]
		longArray.compareAndExchange(1, 12, 45); // if array[1] == 36, replace with 45
		System.out.println("Old value at [2]: " + longArray.getAndSet(2, 72));
		longArray.accumulateAndGet(3, 123L, (acc, number) -> acc + number);
		System.out.println("Array: " + longArray);
		
		System.out.println("\n*** AtomicReferenceArray ***");
		AtomicReferenceArray<String> refArray = new AtomicReferenceArray<>(3);
		refArray.set(0, "Hello");
		refArray.set(1, "Atomic");
		refArray.set(2, "World");
		refArray.accumulateAndGet(2, "!", (acc, text) -> acc + text);
		System.out.println("Array: " + refArray);

	}

}
