package br.com.argonavis.java.concurrency.forkjoin;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.argonavis.java.concurrency.Utils;

class LargestNumberTask3 extends CountedCompleter<Integer> {
	
	private enum Level { MATRIX, LINE, NUMBER };

	private final AtomicInteger largestNumber = new AtomicInteger(0);
	private final int[][] matrix;
	private final Level level;

	LargestNumberTask3(CountedCompleter<Integer> parentTask, int[][] matrix, Level level) {
		super(parentTask);
		this.matrix = matrix;
		this.level = level;
	}

	public LargestNumberTask3(int[][] matrix) {
		this(null, matrix, Level.MATRIX);
	}

	@Override
	public void compute() {
		switch(level) {
		case MATRIX:
			Arrays.stream(matrix).forEach(n -> {
				LargestNumberTask3 lineTask = new LargestNumberTask3(this, new int[][] {n}, Level.LINE);
				lineTask.fork();
				addToPendingCount(1); 
			});
			break;
		case LINE:
			Arrays.stream(matrix[0]).forEach(n -> {
				LargestNumberTask3 numberTask = new LargestNumberTask3(this, new int[][] {{n}}, Level.NUMBER);
				numberTask.fork();
				addToPendingCount(1); 
			});
			break;
		case NUMBER:
			largestNumber.updateAndGet(n -> n < matrix[0][0] ? matrix[0][0]  : n);
			break;
		}
		tryComplete();
	}

	@Override
	public void onCompletion(CountedCompleter<?> completer) {
		LargestNumberTask3 parent = (LargestNumberTask3)this.getCompleter();
		if(parent != null)
		    parent.largestNumber.updateAndGet(n -> n < largestNumber.get() ? largestNumber.get() : n);
		System.out.println(level + ": " + Arrays.deepToString(matrix) + ", L: " + largestNumber);
	}

	@Override
	public Integer getRawResult() {
		return largestNumber.get();
	}
}

public class CountedCompletionDemo {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

		int[][] numbers = {
			{5,18,41,23},
			{6,23,37,19},
			{2, 3,46,51},
			{9, 8,13,12},
		};
		LargestNumberTask3 task = new LargestNumberTask3(numbers);

		int result = task.invoke();
		System.out.println("Result: " + result);
	}
}
