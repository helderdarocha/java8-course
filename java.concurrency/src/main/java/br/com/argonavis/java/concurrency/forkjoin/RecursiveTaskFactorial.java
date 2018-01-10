package br.com.argonavis.java.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

class ForkJoinFactorial extends RecursiveTask<Long> {
	public static final int THRESHOLD = 4; // low threshold is not efficient
	private static final long serialVersionUID = -4191198539308930685L;
	private long number;
	
	public ForkJoinFactorial(Long number) {
		this.number = number;
	}  
	
	private Long sequentialCompute(long n) {
		if(n <= 1)
			return 1L;
		return n * sequentialCompute(n - 1);
	}
	
	@Override
	protected Long compute() {
		if(THRESHOLD > number)
			return sequentialCompute(number);
		ForkJoinFactorial subTask = new ForkJoinFactorial(number-1);
		subTask.fork();
		return number * subTask.join();
	}
}

public class RecursiveTaskFactorial {
	public static void main(String[] args) {
		for (long i = 0; i < 10; i++) {
			ForkJoinFactorial task = new ForkJoinFactorial(i);
			System.out.print(task.invoke() + " ");
		}
	}
}
