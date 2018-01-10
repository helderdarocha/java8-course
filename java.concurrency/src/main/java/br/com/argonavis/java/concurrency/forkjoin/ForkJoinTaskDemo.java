package br.com.argonavis.java.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

class ForkJoinSum extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	private final int psThreshold = 1000;
	private final long[] data;
	private final int start, end;

	public ForkJoinSum(long[] data, int start, int end) {
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long sum = 0;
		if (end - start < psThreshold) {
			sum = LongStream.of(data).map(n -> n).sum();
		} else {
			int mid = (start + end) / 2;
			ForkJoinSum subTask1 = new ForkJoinSum(data, start, mid);
			ForkJoinSum subTask2 = new ForkJoinSum(data, mid, end);
			subTask1.fork();
			subTask2.fork();
			sum = subTask1.join() + subTask2.join();
		}
		return sum;
	}
}

public class ForkJoinTaskDemo {
	public static void main(String[] args) {

		int parallelism = 14; // experiment higher and lower parallelism
		ForkJoinPool pool = new ForkJoinPool(parallelism);

		long[] array = new long[2_000_000]; // experiment with more or less data
		Arrays.parallelSetAll(array, i -> i);
		ForkJoinSum task = new ForkJoinSum(array, 0, array.length);

		ExecutorService status = Executors.newSingleThreadExecutor();
		status.execute(() -> {
			while (!task.isDone()) {
				System.out.println(pool.toString());
				try {
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});

		long clkBegin = System.currentTimeMillis();
		long result = pool.invoke(task);
		// long result = task.invoke();
		long clkEnd = System.currentTimeMillis();

		status.shutdown();
		System.out.println("Result: " + result + ", time: " + (clkEnd - clkBegin) + " ms");
	}

}
