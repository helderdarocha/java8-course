package br.com.argonavis.java.concurrency.atomic.updater;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

class Counter {
	volatile Integer count;

	Counter(Integer initialCount) {
		this.count = initialCount;
	}

	public Integer getCount() {
		return this.count;
	}
	
	@Override public String toString() {
		return ""+count;
	}
}

public class UpdaterExample {

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter(100);
		
		AtomicReferenceFieldUpdater<Counter,Integer> countUpdater = 
				AtomicReferenceFieldUpdater.newUpdater(Counter.class, Integer.class, "count");
		int result = countUpdater.accumulateAndGet(counter, 0, (acc, num) -> acc+1);
		System.out.println(result);

	}

}
