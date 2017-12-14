package br.com.argonavis.java.concurrency.synchronizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;


class CountDownLatchSpreadSheet {
	volatile double[][] data;
	CountDownLatch latch;

	public CountDownLatchSpreadSheet(double[][] data) {
		this.data = data;
	}

	public List<Double> computeSum() {
		int eventCount = data.length; 
		ExecutorService es = Executors.newFixedThreadPool(eventCount); 
		List<Double> lines = Collections.synchronizedList(new ArrayList<Double>());

		latch = new CountDownLatch(eventCount); 
		
		for (double[] line : data) {
			Runnable subtotalProcessor = () -> {
				double subtotal = DoubleStream.of(line).map(i->i).sum();
				lines.add(subtotal);
				System.out.printf("Partial sum of "+Arrays.toString(line)+": R$%1.2f\n", subtotal);
				latch.countDown(); // calculation done
				try {
					System.out.println(Thread.currentThread().getName() + " waiting for countdown: " + latch.getCount() + " left.");
					latch.await(); // wait to compute other partial sums
					System.out.println(Thread.currentThread().getName() + " released.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			es.execute(subtotalProcessor);
		}
		
		try {
			System.out.println(Thread.currentThread().getName() + " waiting for partials: " + latch.getCount() + " left.");
			latch.await(); // main thread should wait until all partials are done
			System.out.println("Main released. Will calculate total now.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

		es.shutdown();
		return lines;
	}
}

public class LatchDemo {
	public static void main(String[] args) throws InterruptedException {
		double[][] values = {{1.0, 2.0, 3.0}, {2.2, 2.2, 5.5, 8.3}, {1.34, 9.11}, {11.9}};
		CountDownLatchSpreadSheet p = new CountDownLatchSpreadSheet(values);
		
		List<Double> lines = p.computeSum();
		
		// This will run only when the latch is opened!
		Runnable totalSumProcesor = () -> System.out.printf("Total sum: R$%1.2f\n", lines.stream().map(i->i).reduce(Double::sum).get());
		(new Thread(totalSumProcesor)).start();
	}
}