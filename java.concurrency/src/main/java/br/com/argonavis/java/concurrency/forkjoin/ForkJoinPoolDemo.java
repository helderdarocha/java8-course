package br.com.argonavis.java.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolDemo {

	public static void main(String[] args) {
		System.getProperties().setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "4");
		
		ForkJoinPool common = ForkJoinPool.commonPool();
		ForkJoinPool pool1 = new ForkJoinPool();
		ForkJoinPool pool2 = new ForkJoinPool(16);
		
		
		System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
		System.out.println("Common Pool Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

		System.out.println("Common: " + common);
		System.out.println("Pool1: " + pool1);
		System.out.println("Pool2: " + pool2);

	}

}
