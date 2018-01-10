package br.com.argonavis.java.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

import br.com.argonavis.java.concurrency.Utils;

class ForkJoinFibonacci extends RecursiveTask<Long> {
	// No parallel threshold (all divide & conquer is parallel)
	private final long number;
	
	public ForkJoinFibonacci(Long number) {
		this.number = number;
	}
	
	@Override
	protected Long compute() {
		if(number < 2)
		    return number;
		ForkJoinFibonacci[] tasks = { new ForkJoinFibonacci(number-1), new ForkJoinFibonacci(number-2) };
		tasks[0].fork();
		tasks[1].fork();
		return tasks[1].join() + tasks[0].join();
	}
	
}

public class RecursiveTaskFibonacci {
	public static void main(String[] args) {
		for (long i = 0; i < 20; i++) {
			ForkJoinFibonacci task = new ForkJoinFibonacci(i);
			System.out.print(task.invoke() + " ");
		}
	}
}
