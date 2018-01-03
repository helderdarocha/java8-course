package br.com.argonavis.java.concurrency.atomic.updater;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicUpdaterExample {
	
	static class SimpleObject {
		volatile long longNumber = 0;
		volatile int intNumber = 0;
		volatile String ref = null;
	}

	public static void main(String[] args) {
		SimpleObject obj = new SimpleObject();

		System.out.println("\n*** AtomicIntegerUpdater ***");
		AtomicIntegerFieldUpdater<SimpleObject> intUpdater = AtomicIntegerFieldUpdater.newUpdater(SimpleObject.class, "intNumber");
		System.out.println("Initial value: " + intUpdater.get(obj));
		System.out.println("After adding: " + intUpdater.addAndGet(obj, 12));
		System.out.println("Replaced: " + intUpdater.compareAndSet(obj, 12, 2500));
		System.out.println("Final value: " + intUpdater.get(obj));
		
		System.out.println("\n*** AtomicLongUpdater ***");
		AtomicLongFieldUpdater<SimpleObject> longUpdater = AtomicLongFieldUpdater.newUpdater(SimpleObject.class, "longNumber");
		System.out.println("Initial value: " + longUpdater.get(obj));
		System.out.println("After adding: " + longUpdater.addAndGet(obj, 50));
		System.out.println("Replaced: " + longUpdater.compareAndSet(obj, 50, Instant.now().toEpochMilli()));
		System.out.println("Final value: " + LocalDateTime.ofEpochSecond(longUpdater.get(obj)/1000L, 0, ZoneOffset.ofHours(-2)));
		
		System.out.println("\n*** AtomicLongUpdater ***");
		AtomicReferenceFieldUpdater<SimpleObject, String> refUpdater = AtomicReferenceFieldUpdater.newUpdater(SimpleObject.class, String.class, "ref");
		System.out.println("Initial value: " + refUpdater.get(obj));
		String newRef = refUpdater.accumulateAndGet(obj, "", (acc, s) -> acc + "Hello");
		System.out.println("After adding: " + newRef);
		System.out.println("Replaced: " + refUpdater.compareAndSet(obj, newRef, "Hello World!"));
		System.out.println("Final value: " + refUpdater.get(obj));

	}

}
