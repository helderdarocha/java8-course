package br.com.argonavis.java.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class RecursiveSum extends RecursiveTask<Long> {
	
	private final int psThreshold = 1000;
	private long[] data;
	int start, end;
	
	public RecursiveSum(long[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long sum = 0;
		if(end - start < psThreshold) {
			sum = LongStream.of(data).map(n->n).sum();
		} else {
			int mid = (start+end)/2;
			RecursiveSum subTask1 = new RecursiveSum(data, start, mid);
			RecursiveSum subTask2 = new RecursiveSum(data, mid, end);
			subTask1.fork();
			subTask2.fork();
			sum = subTask1.join() + subTask2.join();
		}
		if(ForkJoinTask.getPool() != null)
		    System.out.println(ForkJoinTask.getPool().toString());
		return sum;
	}
	
	public static void main(String[] args) {
		
		int parallelism = 1;
		ForkJoinPool pool = new ForkJoinPool(parallelism);
		
		long[] array = new long[2_000_000];
		Arrays.parallelSetAll(array, i->i);
		RecursiveSum task = new RecursiveSum(array, 0, array.length);
		
		long clkBegin = System.currentTimeMillis();
		long result = pool.invoke(task);
		//long result = task.invoke();
		long clkEnd   = System.currentTimeMillis();
		System.out.println(result + ", time: " + (clkEnd - clkBegin) + " ms");
	}

}
