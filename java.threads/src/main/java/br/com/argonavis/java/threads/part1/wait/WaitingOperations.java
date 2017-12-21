package br.com.argonavis.java.threads.part1.wait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WaitingOperations {
	
	private volatile List<Double> results = null;
	volatile boolean sqrtDone = false;
	volatile boolean cbrtDone = false;
	
	public synchronized void createList() {
		results = new ArrayList<>();
		System.out.println("List created. Will notify().");
		notify();
	}

	public synchronized void calculateOddSquareRoots() {
		if(results == null ) {
			try {
				System.out.println("Results object is null. Will wait");
				wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupt on calculateOddSquareRoots() - Terminating.");
				return;
			}
		}
		System.out.println("Got lock. Will now calculateOddSquareRoots().");
		for(int i = 1; i < 100; i += 2) {
			double result = Math.sqrt( i );
			results.add( result );
			try {
				Thread.sleep(50);
			} catch (InterruptedException ignored) {}
		}
		System.out.println("Finished calculateOddSquareRoots(). Will notify().");
		sqrtDone = true;
		notify();
	}
	
	public synchronized void calculateEvenCubicRoots() {
		if(results == null ) {
			try {
				System.out.println("Results object is null. Will wait");
				wait();
			} catch (InterruptedException e) {
				System.out.println("Interrupt on calculateEvenCubicRoots() - Terminating.");
				return;
			}
		}
		System.out.println("Got lock. Will now calculateEvenCubicRoots().");
		for(int i = 2; i <= 1000; i += 2) {
			double result = Math.cbrt( i );
			results.add( result );
			try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {}
		}
		System.out.println("Finished calculateEvenCubicRoots(). Will notify().");
		cbrtDone = true;
		notify();
	}
	
	public List<Double> getResults() {
		return results;
	}
	
}
