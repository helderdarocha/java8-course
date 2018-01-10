package br.com.argonavis.java.concurrency.executors.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import br.com.argonavis.java.concurrency.Utils;

public class FutureTaskDemo2 {

	static class SimpleTask extends FutureTask<Long> {
		SimpleTask(Callable<Long> task) {
			super(task);
		}

		@Override
		public void done() {
			System.out.println("Done.");
		}
	}

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(4);

		Runnable runnableTask = () -> System.out.println("Running");
		Callable<Long> callableTask1 = Executors.callable(runnableTask, 999L);
		FutureTask<Long> simpleTask1 = new SimpleTask(callableTask1);

		service.execute(simpleTask1);
		try {
			Utils.log("FutureTask result 1: " + simpleTask1.get());
		} catch (InterruptedException | ExecutionException e) {
			Utils.log("Exception " + e);
		}
		service.shutdown();
	}
}
