package br.com.argonavis.java.concurrency.forkjoin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.argonavis.java.concurrency.Utils;

class LargestNumberTask4 extends CountedCompleter<Integer> {
	
	private enum Level { MATRIX, LINE, NUMBER };

	private final AtomicInteger largestNumber = new AtomicInteger(0);
	private final int[][] matrix;
	private final Set<LargestNumberTask4> childTasks;
	private final Level level;

	LargestNumberTask4(CountedCompleter<Integer> parentTask, int[][] matrix, Level level) {
		super(parentTask);
		this.matrix = matrix;
		this.childTasks = Collections.synchronizedSet(new HashSet<>());
		this.level = level;
	}

	public LargestNumberTask4(int[][] matrix) {
		this.matrix = matrix;
		this.childTasks = Collections.synchronizedSet(new HashSet<>());
		this.level = Level.MATRIX;
	}

	@Override
	public void compute() {
		switch(level) {
		case MATRIX:
			Arrays.stream(matrix).forEach(n -> {
				LargestNumberTask4 lineTask = new LargestNumberTask4(this, new int[][] {n}, Level.LINE);
				childTasks.add(lineTask);
				lineTask.fork();
				addToPendingCount(1); 
			});
			break;
		case LINE:
			Arrays.stream(matrix[0]).forEach(n -> {
				LargestNumberTask4 numberTask = new LargestNumberTask4(this, new int[][] {{n}}, Level.NUMBER);
				childTasks.add(numberTask);
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
		childTasks.stream()
			.forEach(task -> largestNumber.updateAndGet(n -> n < task.largestNumber.get() ? task.largestNumber.get() : n));
		System.out.println(level + ": " + Arrays.deepToString(matrix) + ", Largest: " + largestNumber);
	}

	@Override
	public Integer getRawResult() {
		return largestNumber.get();
	}
}

public class CountedCompletionDemo2 {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

		int[][] numbers = {
			{5,18,41,23},
			{6,23,37,19},
			{2, 3,46,51},
			{9, 8,13,12},
		};
		LargestNumberTask4 task = new LargestNumberTask4(numbers);

		int result = task.invoke();
		Utils.log(">>> " + result + " <<<");
	}
}
