package br.com.argonavis.java.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.argonavis.java.concurrency.Utils;

class LargestNumberTask extends RecursiveAction {
	final static int THRESHOLD = 5; // limite de tamanho para processamento sequencial
	final AtomicInteger largestNumber;
	final int[] numbers;
	
	public LargestNumberTask(int[] numbers, AtomicInteger largestNumber) {
		Utils.log("New task: " + Arrays.toString(numbers));
		this.numbers = numbers;
		this.largestNumber = largestNumber;
	}
	
	/**
	 * Sequential divide-and-conquer recursive algorithm
	 * @param numbers
	 */
	public void sequentialCompute(int[] numbers) {
		if(numbers.length > 1) {
			int[] left = Arrays.copyOfRange(numbers, 0, numbers.length/2);
			int[] right = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);
			sequentialCompute(left);
			sequentialCompute(right);
		} else if(numbers.length == 1){
			largestNumber.updateAndGet(n -> n < numbers[0] ? numbers[0] : n);
		}
	}

	/**
	 * Parallel divide-and-conquer (fork-join) recursive algorithm
	 */
	protected void compute() {
		if(numbers.length < THRESHOLD) {
			Utils.log("Sequential task: " + Arrays.toString(numbers));
			sequentialCompute(numbers);
		} else {
			int[] left = Arrays.copyOfRange(numbers, 0, numbers.length/2);
			int[] right = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);
			invokeAll(new LargestNumberTask(left, largestNumber), 
					  new LargestNumberTask(right, largestNumber));
		}
	}
}

public class RecursiveActionDemo {
	public static void main(String[] args) {
		int[] numbers = {5,2,79,5,6,4,9,23,4,1,4,64,2,56,3,66,5,2,4,11,54,1,56,2,5,44,35,8,43,7,2,45,3,36,65};
		AtomicInteger result = new AtomicInteger(0);
		LargestNumberTask task = new LargestNumberTask(numbers, result);
		
		task.invoke();
		Utils.log(">>> " + result.get() + " <<<");
	}
}