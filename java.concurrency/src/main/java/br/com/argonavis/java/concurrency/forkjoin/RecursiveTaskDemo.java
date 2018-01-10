package br.com.argonavis.java.concurrency.forkjoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.argonavis.java.concurrency.Utils;

class LargestNumberTask2 extends RecursiveTask<Integer> {
	final static int THRESHOLD = 5; // limite de tamanho para processamento sequencial
	final AtomicInteger largestNumber = new AtomicInteger(0);
	final int[] numbers;
	
	public LargestNumberTask2(int[] numbers) {
		this.numbers = numbers;
	}
	
	/**
	 * Sequential divide-and-conquer recursive algorithm
	 * @param numbers
	 */
	public void sequentialCompute(int[] array) {
		Utils.log("SEQUENTIAL task: " + Arrays.toString(array));
		if(array.length > 1) {
			int[] left = Arrays.copyOfRange(array, 0, array.length/2);
			int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
			sequentialCompute(left);
			sequentialCompute(right);
		} else if(array.length == 1){
			largestNumber.updateAndGet(n -> n < array[0] ? array[0] : n);
		}
	}

	/**
	 * Parallel divide-and-conquer (fork-join) recursive algorithm
	 */
	protected Integer compute() {
		Utils.log("PARALLEL task: " + Arrays.toString(numbers));
		
		if(numbers.length < THRESHOLD) {
			sequentialCompute(numbers);
		} else {
			int[] left = Arrays.copyOfRange(numbers, 0, numbers.length/2);
			int[] right = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);
			RecursiveTask<Integer> task1 = new LargestNumberTask2(left);
			RecursiveTask<Integer> task2 = new LargestNumberTask2(right);
			task1.fork();
			task2.fork();
			int result1 = task1.join(); 
			int result2 = task2.join();
			largestNumber.updateAndGet(n -> n < result1 ? result1 : n); 
			largestNumber.updateAndGet(n -> n < result2 ? result2 : n);
		}
		return largestNumber.get();
	}
}

public class RecursiveTaskDemo {
	public static void main(String[] args) {
		int[] numbers = {5,2,79,5,6,4,9,23,4,1,4,64,2,56,3,66,5,2,4,11,54,1,56,2,5,44,35,8,43,7,2,45,3,36,65};
		LargestNumberTask2 task = new LargestNumberTask2(numbers);
		
		int result = task.invoke();
		Utils.log(">>> " + result + " <<<");
	}
}