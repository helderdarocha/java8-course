package br.com.argonavis.java.concurrency.synchronizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;

class CyclicBarrierSpreadSheet {
	volatile double[][] data;

	public CyclicBarrierSpreadSheet(double[][] data) {
		this.data = data;
	}

	public void computeSum() {
		int threadCount = data.length; // smaller values will compute incomplete sums; larger values will not open barrier
		// int threadCount = data.length / 2; // will open the barrier twice (the sum will be calculated at two stages)
		ExecutorService es = Executors.newFixedThreadPool(threadCount); 
		List<Double> lines = Collections.synchronizedList(new ArrayList<Double>());
		
		// This will run only when (and every time) the barrier is opened!
		Runnable totalSumProcesor = () -> System.out.printf("Total sum: R$%1.2f\n", lines.stream().map(i->i).reduce(Double::sum).get());
		
		CyclicBarrier barrier = new CyclicBarrier(threadCount, totalSumProcesor); // will wait for threadCount threads to arrive before releasing
		
		for (double[] line : data) {
			Runnable subtotalProcessor = () -> {
				double subtotal = DoubleStream.of(line).map(i->i).sum();
				lines.add(subtotal);
				System.out.printf("Partial sum of "+Arrays.toString(line)+": R$%1.2f\n", subtotal);
				try {
					System.out.println(Thread.currentThread().getName() + " waiting.");
					barrier.await(); // wait to compute other partial sums
					System.out.println(Thread.currentThread().getName() + " released.");
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			};
			es.execute(subtotalProcessor);
		}
		es.shutdown();
	}
}

public class BarrierDemo {
	public static void main(String[] args) {
		double[][] values = {{1.0, 2.0, 3.0}, {2.2, 2.2, 5.5, 8.3}, {1.34, 9.11}, {11.9}};
		CyclicBarrierSpreadSheet p = new CyclicBarrierSpreadSheet(values);
		p.computeSum();
	}
}
