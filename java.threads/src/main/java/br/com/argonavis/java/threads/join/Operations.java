package br.com.argonavis.java.threads.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {
	
	private volatile List<Double> results = Collections.synchronizedList(new ArrayList<>());

	public void calculateOddSquareRoots() {
		for(int i = 1; i < 100; i += 2) {
			double result = Math.sqrt( i );
			results.add( result );
			System.out.println("SQRoot of " + i + " = " + result);
			try {
				Thread.sleep(50);
			} catch (InterruptedException ignored) {}
		}
	}
	
	public void calculateEvenCubicRoots() {
		for(int i = 2; i <= 1000; i += 2) {
			double result = Math.cbrt( i );
			results.add( result );
			if(i % 10 == 0) {
			    System.out.println("CBRoot of " + i + " = " + result);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {}
		}
	}
	
	public List<Double> getResults() {
		return results;
	}
	
}
