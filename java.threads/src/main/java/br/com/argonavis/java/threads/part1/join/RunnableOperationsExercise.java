package br.com.argonavis.java.threads.part1.join;

import java.util.List;

public class RunnableOperationsExercise {

	public static void main(String[] args) throws InterruptedException {
		Operations op = new Operations();
		
		Thread[] operations = {
            new Thread( () -> op.calculateOddSquareRoots() ),
            new Thread( () -> op.calculateEvenCubicRoots() )
		};
		
		for(Thread t : operations) {
    		    System.out.println("Iniciando thread.");
    		    t.start();
        }
        
        for(Thread t : operations) {
        		System.out.println("Esperando thread.");
        		t.join();
        }

        List<Double> results = op.getResults();
        Double sum = results.stream().reduce(Double::sum).get();
        System.out.println(results.size() + " results; sum = " + sum + ", avg = " + sum/results.size());
	}

}
