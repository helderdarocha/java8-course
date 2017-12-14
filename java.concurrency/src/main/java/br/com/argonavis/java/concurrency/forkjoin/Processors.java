package br.com.argonavis.java.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class Processors {

	public static void main(String[] args) {
		System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
		System.out.println("Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

	}

}
