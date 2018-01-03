package br.com.argonavis.java.concurrency.synchronizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;

import br.com.argonavis.java.concurrency.NamedPoolThreadFactory;
import br.com.argonavis.java.concurrency.Utils;

class CountDownLatchSpreadSheet { 
	final double[][] data;

	public CountDownLatchSpreadSheet(double[][] data) {
		this.data = data;
	}
	
	private double computeSubtotal(double[] line) {
		Utils.simulatedPause(500);
		return DoubleStream.of(line).map(i->i).sum();
	}

	public List<Double> computeSubtotals() {
		final int numberOfLines = data.length; 
		ExecutorService es = Executors.newFixedThreadPool(numberOfLines, new NamedPoolThreadFactory("THREAD LineItem")); 
		List<Double> computedLines = Collections.synchronizedList(new ArrayList<Double>(numberOfLines));

		CountDownLatch latch = new CountDownLatch(numberOfLines); 
		
		for (double[] line : data) {
			Runnable subtotalProcessor = () -> {
				double subtotal = computeSubtotal(line);
				computedLines.add(subtotal);
				System.out.printf(">>> Partial sum of "+Arrays.toString(line)+": $%1.2f\n", subtotal);
				latch.countDown(); // calculation done
				try {
					Utils.log(" waiting for countdown: " + latch.getCount() + " left.");
					latch.await(); // wait to compute other partial sums
					Utils.log(" released.");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			};
			es.execute(subtotalProcessor);
		}
		
		try {
			Utils.log(" waiting for subtotals: " + latch.getCount() + " left.");
			latch.await(); // main thread should wait until all partials are done
			Utils.log(" released. Will calculate TOTAL now.");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} 

		es.shutdown();
		return computedLines;
	}
}

public class LatchDemo {
	public static void main(String[] args) throws InterruptedException {
		Thread.currentThread().setName("THREAD TotalOrder");
		double[][] values = {{1.0, 2.0, 3.0}, {2.2, 2.2, 5.5, 8.3}, {1.34, 9.11}, {11.9}};
		System.out.println("COMPUTING " + Arrays.deepToString(values));
		List<Double> lines = new CountDownLatchSpreadSheet(values).computeSubtotals();
		
		// This will run only when the latch is opened!
		Runnable totalSumProcesor = () -> System.out.printf(">>> Total sum: $%1.2f\n", lines.stream().map(i->i).reduce(Double::sum).get());
		(new Thread(totalSumProcesor)).start();
	}
}