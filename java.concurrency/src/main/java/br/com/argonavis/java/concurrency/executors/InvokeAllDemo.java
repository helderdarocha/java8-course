package br.com.argonavis.java.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.argonavis.java.concurrency.Utils;

public class InvokeAllDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newFixedThreadPool(4);
		List<Callable<Integer>> tasks = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			tasks.add(() -> {
				Utils.simulatedPause(1000);
				return new Random().nextInt(50) + 1;
			});
		}

		System.out.println("Will start tasks now.");
		List<Future<Integer>> results = service.invokeAll(tasks);
		System.out.println("All tasks invoked.");
		System.out.println("Generated numbers (may repeat): ");
		for (Future<Integer> result : results) {
			System.out.print("[" + result.get() + "]");
		}
		service.shutdown();
	}
}
