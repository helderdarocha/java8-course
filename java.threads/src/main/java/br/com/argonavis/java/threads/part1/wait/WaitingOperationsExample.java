package br.com.argonavis.java.threads.part1.wait;

import java.util.List;

public class WaitingOperationsExample {

	public static void main(String[] args) throws InterruptedException {
		WaitingOperations op = new WaitingOperations();
		
		Thread[] operations = {
            new Thread( () -> op.calculateOddSquareRoots() ),
            new Thread( () -> op.calculateEvenCubicRoots() ),
            new Thread( () -> op.createList() )
		};
		
		for(Thread t : operations) {
    		    System.out.println("Iniciando thread.");
    		    t.start();
        }
        
        synchronized(op) {
        		if(!op.sqrtDone && !op.cbrtDone) {
        			System.out.println("Esperando threads terminarem calculos.");
        			op.wait();
        		}
        		System.out.println("Got lock. Will now calculate everything.");
        }

        List<Double> results = op.getResults();
        Double sum = results.stream().reduce(Double::sum).get();
        System.out.println(results.size() + " results; sum = " + sum + ", avg = " + sum/results.size());
	}

}
